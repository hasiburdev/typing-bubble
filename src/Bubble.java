
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Bubble {

    Random random;

    int positionX;
    int positionY;
    int velocityY;
    int acceleration;
    String answerText;
    String displayText;

    Bubble() {
        random = new Random();

        positionX = random.nextInt(900 - Config.BUBBLE_RADIUS * 2) + Config.BUBBLE_RADIUS;
        positionY = random.nextInt(300) - 300;
        velocityY = 2;
        acceleration = 1;

        generateText();
    }

    public void generateText() {
        answerText = "A";
        displayText = answerText;
    }

    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillOval(positionX - Config.BUBBLE_RADIUS, positionY - Config.BUBBLE_RADIUS, Config.BUBBLE_RADIUS * 2,
                Config.BUBBLE_RADIUS * 2);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospace", Font.BOLD, 20));
        int width = g.getFontMetrics().stringWidth(displayText);
        int height = g.getFontMetrics().getHeight();
        g.drawString(displayText, positionX - width / 2, positionY + height / 2 - 2);
    }

    public void update() {
        positionY += velocityY;
    }

    public boolean checkCollision() {
        if (positionY > Config.GAME_HEIGHT - Config.BUBBLE_RADIUS) {
            positionY = -100;
            positionX = random.nextInt(900 - Config.BUBBLE_RADIUS * 2) + Config.BUBBLE_RADIUS;
            velocityY += acceleration;
            generateText();
            return true;
        }
        return false;
    }

    public boolean checkAnswer(String answer) {
        return answer.equals(answerText);
    }

    public void hideBubble() {
        positionY = -100;
        positionX = random.nextInt(900 - Config.BUBBLE_RADIUS * 2) + Config.BUBBLE_RADIUS;
        velocityY += acceleration;
        generateText();
    }

}
