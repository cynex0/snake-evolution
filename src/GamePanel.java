import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel {
    public static final int CELL_COUNT = 50;
    public static final int CELL_SIZE = (Main.WINDOW_SIZE.x - 2*(BgPanel.MARGIN_DIST)) / CELL_COUNT;

    public static final int FPS = 60;
    private final BgPanel bg;
    private Snake snake;
    private Food food;
    private final Timer gameLoop;

    KeyHandler keyH = new KeyHandler(); //creating an instance of the KeyHandler abstract
    public CollisionControl collisionControl = new CollisionControl(this);
    private boolean restrictLeftRight = false;
    private boolean restrictUpDown = false;


    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH); //To make the gamePanel recognize the key input
        this.setFocusable(true);

        bg = new BgPanel();
        snake = new Snake();
        food = new Food(0, 0);

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

        snake.move();

        collisionControl.checkTile(snake);

    }

    private void produceFood () {
        int locX = generateRandomLoc(Main.WINDOW_SIZE.x / 20 - CELL_SIZE, 10);
        int locY = generateRandomLoc( Main.WINDOW_SIZE.x / 20 - CELL_SIZE, 10);

        food.setFoodLocation(locX,locY);
    }
    //Main.WINDOW_SIZE.x / 20 - 5
    //Main.WINDOW_SIZE.x / CELL_COUNT - CELL_SIZE
    //low: BgPanel.MARGIN_DIST + BgPanel.MARGIN_DIST

    private int generateRandomLoc (int high, int low) {
        int randomLoc = (int) (Math.floor (Math.random() * (1+high-low)) + low) * 20;
        /*
        while (randomLoc >= Main.WINDOW_SIZE.x - (9 * BgPanel.BORDER_SIZE + 14 * BgPanel.MARGIN_DIST) || randomLoc < BgPanel.BORDER_SIZE / 2) {

            randomLoc = (int) (Math.floor (Math.random() * (1+high-low)) + low) * 20;
        }
         */
        return randomLoc;
    }



    /*
    private int generateRandomLoc () {

        int randomLoc = (int) Math.random();

        return  randomLoc;
    }
     */


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintComponent(g);

        food.draw(g);

        Graphics2D frame = (Graphics2D) g;
        snake.draw(frame);
        frame.dispose();

    }

    // starts the game loop
    public void startGame() {
        gameLoop.start();
        produceFood();
    }
}
