import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GamePanel extends JPanel implements KeyListener {
    public static final int CELL_COUNT = 40;
    public static final int CELL_SIZE = GameFrame.WINDOW_SIZE.x / CELL_COUNT;

    public static final int FPS = 60;
    private final BgPanel bg;
    private Snake snake;
    private Food food;
    private final Timer gameLoop;

    private StateChangeListener stateChanger;

    public GamePanel(StateChangeListener listener) {
        super();
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.setFocusable(true);

        bg = new BgPanel();
        snake = new Snake();
        stateChanger = listener;
        food = new Food(0, 0);

        gameLoop = new Timer(1000/FPS, e -> { // GAME LOOP, runs every 1/60th of a second
            update();
            repaint(); // calls paintComponent()
        });
    }

    public void update() {
        // update positions, etc
        snake.move();

        if (snake.doCollisions()) {
            stateChanger.changeState(GameState.GAME_OVER);
            gameLoop.stop();
        }

        if (snake.foodEaten(food)) {
            snake.increaseLength();
            produceFood();
        }
    }

    private void produceFood () {
        int locX = generateRandomLoc(GameFrame.WINDOW_SIZE.x / 15 - CELL_SIZE-3 , 10);
        int locY = generateRandomLoc( GameFrame.WINDOW_SIZE.x / 15 - CELL_SIZE-3, 10);

        food.setFoodLocation(locX,locY);
    }

    private int generateRandomLoc (int high, int low) {
        int randomLoc = (int) (Math.floor (Math.random() * (1+high-low)) + low) * 20;
        return randomLoc;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintComponent(g); // draw background first

        Graphics2D frame = (Graphics2D) g; // frame for drawing 2d graphics

        food.draw(g);
        snake.draw(frame);
        frame.dispose();
    }

    // starts the game loop
    public void startGame() {
        gameLoop.start();
        produceFood();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            if (snake.getDirection() != Direction.DOWN) {
                snake.setDirection(Direction.UP);
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if (snake.getDirection() != Direction.UP) {
                snake.setDirection(Direction.DOWN);
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if (snake.getDirection() != Direction.RIGHT) {
                snake.setDirection(Direction.LEFT);
            }
        }

        if (code == KeyEvent.VK_RIGHT) {
            if (snake.getDirection() != Direction.LEFT) {
                snake.setDirection(Direction.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
