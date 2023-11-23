import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    public static final int CELL_COUNT = 50;
    public static final int CELL_SIZE = (Main.WINDOW_SIZE.x - 2*(BgPanel.MARGIN_DIST)) / CELL_COUNT;

    public static final int FPS = 60;
    private final BgPanel bg;
    private Snake snake;
    private final Timer gameLoop;
    private MainMenu menu;

    KeyHandler keyH = new KeyHandler(); //creating an instance of the KeyHandler abstract
    public CollisionControl collisionControl = new CollisionControl(this);

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
        snake = new Snake();
        menu = new MainMenu();

        gameLoop = new Timer(1000/FPS, e -> { // GAME LOOP, runs every 1/60th of a second
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

        snake.move();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintComponent(g);
        menu.paintComponent(g);

        Graphics2D frame = (Graphics2D) g;

        // draw snake
        frame.setColor(new Color(0x2b331a));
        for (CellPosition pos : snake.getBody()) {
            Point p = pos.getCoordinates();
            frame.fillRect(p.x, p.y, GamePanel.CELL_SIZE, GamePanel.CELL_SIZE);
        }
        frame.dispose();
    }

    // starts the game loop
    public void startGame() {
        gameLoop.start();
    }
}
