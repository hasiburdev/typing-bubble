import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JPanel implements ActionListener {

    Image backgroundImage;

    private GameButton startLevelOne;
    private GameButton startLevelTwo;
    private GameButton startLevelThree;
    private GameButton exitButton;

    WelcomeScreen() {
        this.setPreferredSize(new Dimension(Config.GAME_WIDTH, Config.GAME_HEIGHT));
        this.setFocusable(true);
        this.setLayout(null);

        this.setBackground(Color.CYAN);
        backgroundImage = new ImageIcon(getClass().getResource("./assets/bg.jpg")).getImage();

        startLevelOne = new GameButton("Start Level 1", 185);
        startLevelTwo = new GameButton("Start Level 2", 245);
        startLevelThree = new GameButton("Start Level 3", 305);
        exitButton = new GameButton("Exit", 365);

        startLevelOne.addActionListener(this);
        startLevelTwo.addActionListener(this);
        startLevelThree.addActionListener(this);
        exitButton.addActionListener(this);

        this.add(startLevelOne);
        this.add(startLevelTwo);
        this.add(startLevelThree);
        this.add(exitButton);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
    }

    private void drawGrid(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, Config.GAME_WIDTH, Config.GAME_HEIGHT,
                null);
    }

    private void hidePanel() {
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource() == exitButton) {

            JComponent comp = (JComponent) event.getSource();
            Window window = SwingUtilities.getWindowAncestor(comp);
            window.dispose();

        } else if (event.getSource() == startLevelOne) {

            JComponent comp = (JComponent) event.getSource();
            Window window = SwingUtilities.getWindowAncestor(comp);

            GameLevel levelOne = new LevelOne();

            window.add(levelOne);
            setFocusable(false);
            hidePanel();
            window.pack();

            levelOne.focus();

        } else if (event.getSource() == startLevelTwo) {

            JComponent comp = (JComponent) event.getSource();
            Window window = SwingUtilities.getWindowAncestor(comp);

            GameLevel levelTwo = new LevelTwo();

            window.add(levelTwo);
            window.pack();

            hidePanel();

            levelTwo.focus();

        } else if (event.getSource() == startLevelThree) {

            JComponent comp = (JComponent) event.getSource();
            Window window = SwingUtilities.getWindowAncestor(comp);

            GameLevel levelThree = new LevelThree();

            window.add(levelThree);
            window.pack();

            hidePanel();

            levelThree.focus();

        }
    }
}
