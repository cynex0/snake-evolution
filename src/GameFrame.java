import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public static final Point WINDOW_SIZE = new Point(800, 800);
    private static JPanel currentPanel;

    public GameFrame() {
        super();
        currentPanel = new MainMenu();
        this.add(currentPanel);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Snake Evolution");
        this.setSize(WINDOW_SIZE.x, WINDOW_SIZE.y);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void changeState(GameState newState) {
        this.remove(currentPanel);
        switch (newState) {
            case MENU -> {
                currentPanel = new MainMenu();
            }
            case GAME -> {
                currentPanel = new GamePanel();
            }
            case GAME_OVER -> {

            }
            case GAME_OVER_ENTERNAME -> {
            }
            case LEADERBOARD -> {
            }
        }
        this.add(currentPanel);
    }
}
