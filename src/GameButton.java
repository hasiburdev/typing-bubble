import javax.swing.JButton;

public class GameButton extends JButton {
    GameButton(String text, int positionY) {
        super(text);
        this.setFocusable(false);
        this.setBounds(350, positionY, 200, 50);

    }
}
