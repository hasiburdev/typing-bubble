import java.awt.Window;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

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

            scoreClip.stop();
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

    @Override
    public void startNextLevel() {
        JComponent comp = (JComponent) this;
        Window window = SwingUtilities.getWindowAncestor(comp);

        GameLevel levelTwo = new LevelTwo();

        window.add(levelTwo);
        setFocusable(false);
        window.pack();

        levelTwo.focus();
    }

}
