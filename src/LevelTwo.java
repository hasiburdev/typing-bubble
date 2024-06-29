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

        if (remaingLife == 0) {
            return;
        }

        if (event.getKeyCode() == 16 || event.getKeyCode() == 20) {
            return;
        }

        System.out.println(typedString);

        if (typedString.length() >= 3) {

            Bubble bubbleToMove = null;
            int maxHeight = 0;

            for (Bubble bubble : bubbles) {

                if (bubble.answerText.equals(typedString) &&
                        bubble.positionY > 0) {

                    if (bubble.positionY > maxHeight) {
                        maxHeight = bubble.positionY;
                        bubbleToMove = bubble;
                    }
                }
            }

            if (bubbleToMove != null) {
                score++;
                typedString = "";
                bubbleToMove.hideBubble();

                scoreClip.setFramePosition(0);
                scoreClip.start();
                scoreClip.loop(0);

                return;
            } else {
                lifeClip.stop();
                lifeClip.setFramePosition(0);
                lifeClip.start();
                lifeClip.loop(0);
                remaingLife--;

                typedString = "";
            }
        }
    }
}
