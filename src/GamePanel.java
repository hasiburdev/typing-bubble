import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 900;
    static final int SCREEN_HEIGHT = 600;
    static final int BUBBLE_SIZE = 50;
    static final int BUBBLE_COUNT = 10;
    static final int DELAY_TIME = 75;

    boolean running = false;
    Timer timer;
    Random random;
    Image backgroundImage;
    int remaingLife = 5;
    int score = 0;
    int target = 40;

    Bubble[] bubbles = new Bubble[BUBBLE_COUNT];

    GamePanel() {

        random = new Random();
        backgroundImage = new ImageIcon(getClass().getResource("./assets/background.jpg")).getImage();

        for (int i = 0; i < BUBBLE_COUNT; i++) {
            bubbles[i] = new Bubble();
        }

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.CYAN);
        this.setFocusable(true);
        this.addKeyListener(new InnerKeyAdapter());

        startGame();
    }

    public void startGame() {
        running = true;
        timer = new Timer(DELAY_TIME, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void showInformation(Graphics g) {
        g.setFont(new Font("Monospace", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 25);
        g.drawString("Target: " + target, 10, 50);
        g.drawString("Life: " + remaingLife, 10, 75);
    }

    public void drawGrid(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, null);
    }

    public void drawBubbles(Graphics g) {
        for (Bubble bubble : bubbles) {
            bubble.draw(g);
        }
    }

    public void draw(Graphics g) {
        drawGrid(g);
        drawBubbles(g);
        showInformation(g);
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
                System.out.println("Life: " + remaingLife);
            }
        }

    }

    public void checkRemainigLife() {
        if (remaingLife <= 0) {
            running = false;
        }
    }

    public void checkTarget() {
        if (score >= target) {
            running = false;
        }
    }

    public void gameOver(Graphics g) {
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

    public class InnerKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    System.out.println("Left");
                    break;

                default:
                    break;
            }
        }
    }
}
