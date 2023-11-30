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
                currentPanel = gamePanel;
                gamePanel.startGame();
            }
            case GAME_OVER -> {

            }
            case GAME_OVER_ENTERNAME -> {
            }
            case LEADERBOARD -> {
            }
        }
        this.add(currentPanel);
        this.pack();
    }
}
