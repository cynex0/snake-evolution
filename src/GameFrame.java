import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements StateChangeListener {
    public static final Point WINDOW_SIZE = new Point(800, 800);
    private static JPanel currentPanel;
    private StateChangeListener stateChangeListener;


    public GameFrame() {
        super();

        currentPanel = new MainMenu(this);
        this.add(currentPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Snake Evolution");
        this.setSize(WINDOW_SIZE.x, WINDOW_SIZE.y);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setFocusable(true);
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
                GamePanel game = (GamePanel) currentPanel;
                int score = game.getScore();

                currentPanel = new GameOver(this,score,true);//,100, Leaderboard.istop10(score)); // waiting for the function
            }

            case LEADERBOARD -> {
            }
        }
        this.add(currentPanel);
        this.pack();
    }
}
