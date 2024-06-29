import java.awt.event.KeyEvent;

public class LevelThree extends GameLevel {

    public void generateBubbles() {
        for (int i = 0; i < Config.BUBBLE_COUNT; i++) {
            bubbles[i] = new BubbleMath();
        }
    }

    @Override
    public void checkKeyTyped(KeyEvent event) {
        System.out.println("From child level - 3");

        if (remaingLife == 0) {
            return;
        }

        if (event.getKeyCode() == 16 || event.getKeyCode() == 20) {
            return;
        }

        typedString = "";

        int maxHeight = 0;
        Bubble bubbleToMove = null;

        for (Bubble bubble : bubbles) {

            if (bubble.answerText.equals(String.valueOf(event.getKeyChar())) && bubble.positionY > 0) {

                if (bubble.positionY > maxHeight) {
                    maxHeight = bubble.positionY;
                    bubbleToMove = bubble;
                }
            }
        }

        if (bubbleToMove != null) {
            score++;

            scoreClip.setFramePosition(0);
            scoreClip.start();
            scoreClip.loop(0);
            bubbleToMove.hideBubble();
            return;
        }

        lifeClip.setFramePosition(0);
        lifeClip.start();
        lifeClip.loop(0);
        remaingLife--;
    }

}
