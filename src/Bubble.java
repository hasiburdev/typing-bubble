import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Bubble {

    static final int BUBBLE_RADIUS = 25;
    int positionX;
    int positionY;
    int velocityY;
    int acceleration;
    String letter;
    boolean showBubble;

    // string of all lowercase letters
    String[] letters = "abcdefghijklmnopqrstuvwxyz".split("");

    // string of all uppercase and lowercase letters
    String[] lettersHard = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    Random random;

    Bubble() {
        random = new Random();
        letter = letters[random.nextInt(letters.length)];

        showBubble = true;

        positionX = random.nextInt(900 - BUBBLE_RADIUS * 2) + BUBBLE_RADIUS;
        positionY = random.nextInt(200) - 100;
        velocityY = 3;
        acceleration = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(positionX - BUBBLE_RADIUS, positionY - BUBBLE_RADIUS, BUBBLE_RADIUS * 2, BUBBLE_RADIUS * 2);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospace", Font.BOLD, 20));
        int width = g.getFontMetrics().stringWidth(letter);
        int height = g.getFontMetrics().getHeight();
        g.drawString(letter, positionX - width / 2, positionY + height / 2 - 2);
    }

    public void update() {
        positionY += velocityY;
        velocityY += acceleration;
    }

    public boolean checkCollision() {
        if (positionY > 600) {
            positionY = -100;
            velocityY += 1;
            showBubble = false;
            return true;
        }
        return false;
    }

    public void hideBubble() {
        positionY = -100;
        velocityY += 1;
        showBubble = false;
    }

}
