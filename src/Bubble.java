import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Bubble {

    static final int BUBBLE_RADIUS = 25;

    int positionX;
    int positionY;
    int velocityY;
    int acceleration;
    String letter;

    // string of all letters
    String[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

    Random random;

    Bubble() {
        random = new Random();
        letter = letters[random.nextInt(letters.length)];

        positionX = random.nextInt(900);
        positionY = random.nextInt(600);
        velocityY = random.nextInt(5) + 1;
        acceleration = 0;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(positionX - BUBBLE_RADIUS, positionY - BUBBLE_RADIUS, 50, 50);
        g.setColor(Color.BLACK);
        g.drawString(letter, positionX - 5, positionY + 5);
    }

    public void update() {
        positionY += velocityY;
        velocityY += acceleration;
    }

    public boolean checkCollision() {
        if (positionY > 600) {
            positionY = 0;
            positionX = random.nextInt(900);
            velocityY = random.nextInt(5) + 1;
            return true;
        }
        return false;
    }

}
