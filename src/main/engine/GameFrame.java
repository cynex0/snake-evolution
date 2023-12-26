package main.engine;

import panels.Leaderboard;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import panels.*;

public class GameFrame extends JFrame implements StateChangeListener {
    public static final Point WINDOW_SIZE = new Point(800, 800);
    private static JPanel currentPanel;
    private StateChangeListener stateChangeListener;


    public GameFrame() {
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Snake Evolution");
        this.setSize(WINDOW_SIZE.x, WINDOW_SIZE.y);

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
        }
        this.add(currentPanel);
        this.pack();
    }
}
