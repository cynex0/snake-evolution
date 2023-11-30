import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {
    public static final int CELL_COUNT = 40;
    public static final int CELL_SIZE = GameFrame.WINDOW_SIZE.x / CELL_COUNT;

    public static final int FPS = 60;
    private final BgPanel bg;
    private Snake snake;
    private final Timer gameLoop;

    private StateChangeListener stateChanger;

    KeyHandler keyH = new KeyHandler(); //creating an instance of the KeyHandler abstract
    public CollisionControl collisionControl = new CollisionControl(this);


    public GamePanel(StateChangeListener listener) {
        super();
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH); //To make the gamePanel recognize the key input
        this.setFocusable(true);

        bg = new BgPanel();
        snake = new Snake();
        stateChanger = listener;

        gameLoop = new Timer(1000/FPS, e -> { // GAME LOOP, runs every 1/60th of a second
            update();
            repaint(); // calls paintComponent()
        });

    }

    public void update() {
        // update positions, etc
        if (keyH.upPressed && snake.getDirection() != Direction.DOWN) {
            snake.setDirection(Direction.UP);
        }
        else if (keyH.downPressed && snake.getDirection() != Direction.UP) {
            snake.setDirection(Direction.DOWN);
        }
        else if (keyH.rightPressed && snake.getDirection() != Direction.LEFT) {
            snake.setDirection(Direction.RIGHT);
        }
        else if (keyH.leftPressed && snake.getDirection() != Direction.RIGHT) {
            snake.setDirection(Direction.LEFT);
        }

        if (snake.doCollisions()) {
            System.out.println("COLLIDED"); // DEBUG
            stateChanger.changeState(GameState.GAME_OVER);
            gameLoop.stop();
        }

        snake.move();
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("drawing");
        super.paintComponent(g);
        bg.paintComponent(g); // always draw background first

        Graphics2D frame = (Graphics2D) g; // frame for drawing 2d graphics

        snake.draw(frame);
        frame.dispose();
    }

    // starts the game loop
    public void startGame() {
        System.out.println("Started");
        gameLoop.start();
    }
}