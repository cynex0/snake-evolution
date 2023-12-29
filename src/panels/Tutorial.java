package panels;

import main.engine.GameState;
import main.engine.StateChangeListener;
import utilities.GameButton;
import utilities.GameConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents a tutorial screen.
 * @author Victoria Rönnlid
 */
public class Tutorial extends JPanel implements ActionListener {
    private GameButton menuBtn;

    private BufferedImage tutorialPic;
    private StateChangeListener stateChanger;

    /**
     * Creates the screen, loads tutorial image and applies layout.
     * @author Victoria Rönnlid
     * @author Maksims Orlovs (co-author)
     * @param listener
     */
    public Tutorial(StateChangeListener listener) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameConstants.WINDOW_SIZE.x, GameConstants.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000")); // sets the color to the nokia snake green background color.

        try {
            tutorialPic = ImageIO.read(new File("res/HowToPlaySnake.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        menuBtn = new GameButton("Main Menu");
        menuBtn.addActionListener(this);
        menuBtn.setActionCommand(menuBtn.getText());
        this.add(Box.createRigidArea(new Dimension(0, 650))); // adds empty area before MM button
        this.add(menuBtn);

        stateChanger = listener;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(tutorialPic,0,0,null);
    }

    @Override
    public void actionPerformed(ActionEvent event) { // logic for when buttons are clicked.
        String actionCommand = event.getActionCommand();

        if ("Main Menu".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU); // switches to state MENU.
        }
    }
}
