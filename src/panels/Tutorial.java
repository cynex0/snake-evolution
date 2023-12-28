package panels;

import main.engine.GameState;
import main.engine.StateChangeListener;
import utilities.GameButton;
import utilities.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tutorial extends JPanel implements ActionListener {
    private BgPanel backPanel;
    private GameButton menuBtn;

    private ImageIcon tutorialPic;
    private StateChangeListener stateChanger;

    public Tutorial(StateChangeListener listener) {
        backPanel = new BgPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameConstants.WINDOW_SIZE.x, GameConstants.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000")); // sets the color to the nokia snake green background color.

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameConstants.WINDOW_SIZE.x, GameConstants.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000")); // sets the color to the nokia snake green background color.

        tutorialPic = new ImageIcon("res/HowToPlaySnake.png");
        JLabel label = new JLabel(tutorialPic);
        menuBtn = new GameButton("Main Menu");
        this.add(backPanel);
        this.add(label);
        this.add(menuBtn);
        stateChanger = listener;
    }

    @Override
    public void actionPerformed(ActionEvent event) { // logic for when buttons are clicked.
        String actionCommand = event.getActionCommand();

        if ("Main Menu".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU); // switches to state MENU.
        }
    }
}
