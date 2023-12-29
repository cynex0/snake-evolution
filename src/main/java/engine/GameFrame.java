package engine;

import panels.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import utilities.GameConstants;


/**
 * The GameFrame class is a part of the Game Engine system that handles switching and displaying appropriate game
 * states.
 * It extends JFrame to use Swing for displaying a game window and implements StateChangeListener to receive requests
 * from the states to switch to a different state.
 * @author Maksims Orlovs
 */
public class GameFrame extends JFrame implements StateChangeListener {
    private static JPanel currentPanel;

    /**
     * Creates a GameFrame and sets up the game, the window and the font.
     * @author Maksims Orlovs
     * @author Halah Hasani (co-author)
     */
    public GameFrame() {
        super();

        // window setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Snake Evolution");
        this.setSize(GameConstants.WINDOW_SIZE.x, GameConstants.WINDOW_SIZE.y);

        this.setLocationRelativeTo(null);
        this.setFocusable(true);

        // create font for use in all panels
        try {
            Font gameFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/PublicPixel.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(gameFont); // makes the font available to font constructors by font name
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException("Font creation error\n" + e);
        }

        changeState(GameState.MENU);
        this.setVisible(true);
    }

    /**
     * Receives the call to switch to the next state, handles logic required for each state.
     * @param newState the next state of the game
     * @author Maksims Orlovs
     */
    @Override
    public void changeState(GameState newState) {
        getContentPane().removeAll();
        switch (newState) {
            case MENU -> {
                currentPanel = new MainMenu(this);
            }

            case GAME -> {
                GamePanel gamePanel = new GamePanel(this);
                this.addKeyListener(gamePanel);
                gamePanel.requestFocusInWindow();
                currentPanel = gamePanel;
                gamePanel.startGame();
            }

            case GAME_OVER -> {
                int score = ((GamePanel)currentPanel).getScore(); // casting is safe, previous panel guaranteed to be panels.GamePanel
                GameOver nextPanel = new GameOver(this, score, false);
                currentPanel = nextPanel;
            }

            case GAME_OVER_ENTERNAME -> {
                int score = ((GamePanel)currentPanel).getScore();
                GameOver nextPanel = new GameOver(this, score, true);
                currentPanel = nextPanel;
            }

            case LEADERBOARD -> {
                currentPanel = new Leaderboard(this);
            }
            case TUTORIAL -> {
                currentPanel = new Tutorial(this);
            }
        }
        this.add(currentPanel);
        this.pack();
    }
}
