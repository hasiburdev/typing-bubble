import java.awt.event.KeyEvent;

public class LevelTwo extends GameLevel {

    public void generateBubbles() {
        for (int i = 0; i < Config.BUBBLE_COUNT; i++) {
            bubbles[i] = new BubbleWord();
        }
    }

    @Override
    public void checkKeyTyped(KeyEvent event) {
        System.out.println("From child level - 2");

        if (event.getKeyCode() == 16 || event.getKeyCode() == 20) {
            return;
        }

        System.out.println(typedString);

        if (typedString.length() == 3) {

            for (Bubble bubble : bubbles) {

                if (bubble.answerText.equals(typedString) &&
                        bubble.positionY > 0) {
                    score++;
                    typedString = "";
                    bubble.hideBubble();
                    return;
                }
            }

        }

        // if (event.getKeyCode() == 16 || event.getKeyCode() == 20) {
        // return;
        // }

        // for (Bubble bubble : bubbles) {

        // if (bubble.answerText.equals(String.valueOf(event.getKeyChar())) &&
        // bubble.positionY > 0) {
        // score++;
        // bubble.hideBubble();
        // return;
        // }
        // }

        // remaingLife--;
    }
}
