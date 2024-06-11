import java.awt.event.KeyEvent;

public class LevelOne extends GameLevel implements InterfaceLevel {
    LevelOne() {
    }

    public void generateBubbles() {
        for (int i = 0; i < Config.BUBBLE_COUNT; i++) {
            bubbles[i] = new BubbleLetter();
        }
    }

    @Override
    public void checkKeyTyped(KeyEvent event) {
        System.out.println("From child level - 1");

        if (event.getKeyCode() == 16 || event.getKeyCode() == 20) {
            return;
        }

        for (Bubble bubble : bubbles) {

            if (bubble.answerText.equals(String.valueOf(event.getKeyChar())) && bubble.positionY > 0) {
                score++;
                bubble.hideBubble();
                return;
            }
        }

        remaingLife--;
    }

}
