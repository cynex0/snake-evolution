import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private BgPanel bg;
    Thread gameThread;

    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        bg = new BgPanel();
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
        gameThread = new Thread(this);
        gameThread.start();
    }

    // GAME LOOP
    public void run() {
        while(gameThread != null) {
            update();
            repaint(); // calls paintComponent
        }
    }
}
