import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;
import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameLevel extends JPanel implements ActionListener {

    Timer timer;
    Random random;
    Image backgroundImage;
    Clip backgroundClip;
    Clip gameOverClip;
    Clip scoreClip;
    Clip lifeClip;

    boolean running = false;
    int remaingLife = Config.REMAINING_LIFE;
    int score = 0;
    int target = Config.TARGET;

    String typedString = "";

    Bubble[] bubbles = new Bubble[Config.BUBBLE_COUNT];

    GameLevel() {

        random = new Random();
        backgroundImage = new ImageIcon(getClass().getResource("./assets/bg.jpg")).getImage();

        this.setPreferredSize(new Dimension(Config.GAME_WIDTH, Config.GAME_HEIGHT));
        this.setBackground(Color.CYAN);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(new InnerKeyAdapter());

        try {
            backgroundClip = AudioSystem.getClip();
            File file = new File("src/assets/sound/background.wav").getAbsoluteFile();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            backgroundClip.open(inputStream);
            backgroundClip.start();
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);

            gameOverClip = AudioSystem.getClip();
            File gameOverFile = new File("src/assets/sound/game-over.wav").getAbsoluteFile();
            AudioInputStream gameOverInputStream = AudioSystem.getAudioInputStream(gameOverFile);
            gameOverClip.open(gameOverInputStream);

            scoreClip = AudioSystem.getClip();
            File scoreFile = new File("src/assets/sound/score.wav").getAbsoluteFile();
            AudioInputStream scoreInputStream = AudioSystem.getAudioInputStream(scoreFile);
            scoreClip.open(scoreInputStream);

            lifeClip = AudioSystem.getClip();
            File lifeFile = new File("src/assets/sound/life.wav").getAbsoluteFile();
            AudioInputStream lifeInputStream = AudioSystem.getAudioInputStream(lifeFile);
            lifeClip.open(lifeInputStream);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        generateBubbles();
        startGame();

    }

    public void focus() {
        setFocusable(true);
        requestFocus();
    }

    public void generateBubbles() {
        for (int i = 0; i < Config.BUBBLE_COUNT; i++) {
            bubbles[i] = new Bubble();
        }
    }

    public void showInformation(Graphics g) {
        g.setFont(new Font("Monospace", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 25);
        g.drawString("Target: " + target, 10, 50);
        g.drawString("Life: " + remaingLife, 10, 75);

        // g.drawString("Typed: " + typedString, 10, 570);
    }

    private void drawGrid(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, Config.GAME_WIDTH, Config.GAME_HEIGHT, null);
    }

    public void drawBubbles(Graphics g) {
        for (Bubble bubble : bubbles) {
            bubble.draw(g);
        }
    }

    public void moveBubble() {
        for (Bubble bubble : bubbles) {
            bubble.update();
        }
    }

    public void checkBubbleDrop() {

        for (Bubble bubble : bubbles) {
            if (bubble.checkCollision()) {
                remaingLife--;
                bubble.hideBubble();
            }
        }

    }

    public void checkRemainigLife() {
        if (remaingLife <= 0) {
            running = false;
        }
    }

    public void draw(Graphics g) {
        drawGrid(g);
        drawBubbles(g);
        showInformation(g);
        gameOver(g);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void checkTarget() {
        if (score >= target) {
            running = false;
        }
    }

    public void startNextLevel() {
        System.out.println("Implement this method in the child class");
    }

    // public void gameWin

    public void gameOver(Graphics g) {
        if (isGameOver()) {
            g.setFont(new Font("Monospace", Font.BOLD, 50));
            g.setColor(Color.MAGENTA);

            if (score >= target) {
                int width = g.getFontMetrics().stringWidth("Congratulations! You Won!");
                int height = g.getFontMetrics().getHeight();
                g.drawString("Congratulations! You Won!", Config.GAME_WIDTH / 2 - width / 2,
                        Config.GAME_HEIGHT / 2 - height / 2);

                if (this instanceof LevelOne || this instanceof LevelTwo) {
                    int widthNext = g.getFontMetrics().stringWidth("Proceeding to Next Level...");
                    int heightNext = g.getFontMetrics().getHeight();
                    g.drawString("Proceeding to Next Level...", Config.GAME_WIDTH / 2 - widthNext / 2,
                            Config.GAME_HEIGHT / 2 - heightNext / 2 + 50);
                }

                backgroundClip.stop();
                gameOverClip.start();

                int delay = 2000;
                Timer timer = new Timer(delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        System.out.println("Game Won");
                        startNextLevel();

                    }
                });
                timer.setRepeats(false);
                timer.start();

            } else {

                int width = g.getFontMetrics().stringWidth("Game Over");
                int height = g.getFontMetrics().getHeight();
                g.drawString("Game Over", Config.GAME_WIDTH / 2 - width / 2, Config.GAME_HEIGHT / 2 - height / 2);

                backgroundClip.stop();
                gameOverClip.start();
            }

        }
    }

    public boolean isGameOver() {
        return !running;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        moveBubble();
        checkBubbleDrop();
        checkRemainigLife();
        checkTarget();

        repaint();

        if (!running) {

            timer.stop();

        }
    }

    public void startGame() {
        running = true;
        timer = new Timer(Config.DELAY_TIME, this);
        timer.start();

    }

    public void checkKeyTyped(KeyEvent event) {
        System.out.println("Implement this method in the child class");
    }

    public class InnerKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            System.out.println("Key Pressed: " + e.getKeyChar());

            String lettersAndNumbers = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

            if (lettersAndNumbers.contains(Character.toString(e.getKeyChar()))) {
                typedString = typedString + e.getKeyChar();
            }

            checkKeyTyped(e);
        }
    }
}
