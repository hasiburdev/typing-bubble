import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameLevel extends JPanel implements ActionListener {

    Timer timer;
    Random random;
    Image backgroundImage;

    boolean running = false;
    int remaingLife = 5;
    int score = 0;
    int target = 40;

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

    public void gameOver(Graphics g) {
        if (isGameOver()) {
            g.setFont(new Font("Monospace", Font.BOLD, 50));
            g.setColor(Color.MAGENTA);

            int width = g.getFontMetrics().stringWidth("Game Over");
            int height = g.getFontMetrics().getHeight();
            g.drawString("Game Over", Config.GAME_WIDTH / 2 - width / 2, Config.GAME_HEIGHT / 2 - height / 2);
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
                // return;
            }

            checkKeyTyped(e);
        }
    }
}
