import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GamePanel extends JPanel {
    public static final int FPS = 60;
    private BgPanel bg;
    Timer gameTimer;

    KeyHandler keyH = new KeyHandler(); //creating an instance of the KeyHandler abstract

    //set snake's default position
    int snakeX = 100;
    int snakeY = 100;
    int snakeSpeed = 4;

    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH); //To make the gamePanel recognize the key input
        this.setFocusable(true);

        bg = new BgPanel();

        gameTimer = new Timer(1000/FPS, e -> { // GAME LOOP
            update();
            repaint(); // calls paintComponent()
        });
    }

    public void update() {
        // update positions, etc
        if (keyH.upPressed) {
            snakeY = snakeY - snakeSpeed;
        }
        else if (keyH.downPressed) {
            snakeY = snakeY + snakeSpeed;
        }
        else if (keyH.rightPressed) {
            snakeX = snakeX + snakeSpeed;
        }
        else if (keyH.leftPressed) {
            snakeX = snakeX - snakeSpeed;
        }
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
