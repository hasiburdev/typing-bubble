
public class Game {
    Game() {
        GameFrame frame = new GameFrame();
        // GamePanel panel = new GamePanel();
        frame.add(new WelcomeScreen());
        frame.pack();
        frame.setLocationRelativeTo(null);

        // panel.focus();

    }
}
