import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel {
    public static final int FPS = 60;
    private BgPanel bg;
    Timer gameTimer;

    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        bg = new BgPanel();

        gameTimer = new Timer(1000/FPS, e -> { // GAME LOOP
            update();
            repaint(); // calls paintComponent()
        });
    }

    public void update() {
        // update positions, etc
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintComponent(g);
    }

    // starts the game loop
    public void startGame() {
        gameTimer.start();
    }
}
