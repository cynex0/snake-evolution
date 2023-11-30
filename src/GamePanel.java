import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class GamePanel extends JPanel {
    public static final int CELL_COUNT = 50;
    public static final int CELL_SIZE = (Main.WINDOW_SIZE.x - 2*(BgPanel.MARGIN_DIST)) / CELL_COUNT;

    public static final int FPS = 60;
    private final BgPanel bg;
    private Snake snake;
    private final Timer gameLoop;

    private GameState state;
    private final GameOver gameOver;
   // private LeaderBoard lb;

    KeyHandler keyH = new KeyHandler(); //creating an instance of the KeyHandler abstract
    public CollisionControl collisionControl = new CollisionControl(this);


    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH); //To make the gamePanel recognize the key input
        this.setFocusable(true);

        bg = new BgPanel();
        snake = new Snake();
       gameOver = new GameOver();

        state = GameState.GAME; // initial state TODO: change to MENU
       // lb = new LeaderBoard();

      gameLoop = new Timer(1000/FPS, e -> { // GAME LOOP, runs every 1/60th of a second
           update();
            repaint(); // calls paintComponent()
       });
    }

    public void update() {
        // update positions, etc
        switch (state) {
            case MENU -> {
                // read user input, update selected button, switch state if button pressed
            }
            case GAME -> {
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
                    state = GameState.GAME_OVER;
                }

                snake.move();
            }
            case GAME_OVER -> {
                // read user input, update selected button, switch state if button pressed
            }
            case GAME_OVER_ENTERNAME -> {
                // read user input, update selected button, switch state if button pressed
            }
            case LEADERBOARD -> {
                // read user input, update selected button, switch state if button pressed
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintComponent(g); // always draw background first
        bg.paintComponent(g);
        //lb.paintComponent(g);

        Graphics2D frame = (Graphics2D) g; // frame for drawing 2d graphics

        switch (state) {
            case MENU -> {
                // draw menu elements
            }
            case GAME -> {
                snake.draw(frame);
            }
            case GAME_OVER -> {
                // draw "game over" elements
            }
            case GAME_OVER_ENTERNAME -> {
                // draw "game over" elements + name prompt
            }
            case LEADERBOARD -> {
                // draw leaderboard elements
            }
        }
        frame.dispose();
    }

    // starts the game loop
    public void startGame() {
        gameLoop.start();
    }


}

