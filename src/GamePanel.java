import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Random;


public class GamePanel extends JPanel implements KeyListener {
    public static final int CELL_COUNT = 40;
    public static final int CELL_SIZE = GameFrame.WINDOW_SIZE.x / CELL_COUNT;
    public static final int FPS = 60;

    private BgPanel bg;
    private Snake snake;
    private ArrayList<Food> food;
    private Random rand;
    private ObstacleList obstacles;

    private int score;
    private final Timer gameLoop;
    private long startTime;
    private boolean fastMode, slowMode;
    private StateChangeListener stateChanger;


    public GamePanel(StateChangeListener listener) {
        super();
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        rand = new Random();
        bg = new BgPanel();
        snake = new Snake();

        stateChanger = listener;
        food = new ArrayList<>();
        food.add(new Food());
        obstacles = new ObstacleList();
        score = 0;
        startTime = 0;
        fastMode = false;
        slowMode = false;

        int delay = (int) (1000 / (FPS * Snake.SPEED));
        gameLoop = new Timer(delay, e -> { // GAME LOOP, runs every 1/60*SPEED -th of a second

            //1000/(int)(FPS * Snake.SPEED)
            update();
            repaint(); // calls paintComponent()

        });
    }

    public void update() {
        // update positions, etc
        if (fastMode || slowMode) {
            if (System.currentTimeMillis() - startTime > 5000) {
                fastMode = false;
                slowMode = false;
                adjustSnakeSpeed(1); // Set the speed back to normal
            }
        }

        snake.move(); // add a new "head" based on the movement direction

        // snake collisions (self and borders)
        if (snake.doCollisions()) {
            stopGame();
            return;
        }

        // collision with obstacles
        if (snake.checkCollisionWith(obstacles.getAllCells())) {
            stopGame();
            return;
        }

        // collision with food
        boolean incLenght = false;
        ArrayList<Food> newFoodList = new ArrayList<>();

        for (Iterator<Food> it = food.iterator(); it.hasNext();) {
            Food foodItem = it.next();
            if (snake.checkCollisionWith(foodItem.getFoodLocation())) {
                it.remove(); // remove consumed food from list

                switch (foodItem.getFoodType()) {
                    case DEFAULT -> {
                        incLenght = true;
                        score++;

                        // spawn new default food in a valid position
                        CellPosition newFoodPos;
                        Food newFood;
                        do {
                            newFood = new Food(); // respawn food until its in a valid position
                            newFoodPos = newFood.getFoodLocation();
                        } while (snake.checkCollisionWith(newFoodPos) || obstacles.getAllCells().contains(newFoodPos));
                        newFoodList.add(newFood);
                    }

                    case SPEEDFOOD -> {
                        fastMode = true;
                        adjustSnakeSpeed(2);
                        startTime = System.currentTimeMillis();
                    }

                    case SLOWFOOD -> {
                        slowMode = true;
                        adjustSnakeSpeed(0.5);
                        startTime = System.currentTimeMillis();
                    }

                    case PLUSFOOD -> {
                        score += 2;
                        incLenght = true;
                    }

                    case MINUSFOOD -> {
                        score -= 2;
                        if (score < 0)
                            score = 0;
                    }
                }

                // 20% to spawn new bonus food in a valid position
                if (food.size() < 2 && rand.nextFloat() <= 0.2) {
                    CellPosition newFoodPos;
                    Food bonusFood;
                    do {
                        bonusFood = new BonusFood(); // respawn food until its in a valid position
                        newFoodPos = bonusFood.getFoodLocation();
                    } while (snake.checkCollisionWith(newFoodPos) || obstacles.getAllCells().contains(newFoodPos));
                    newFoodList.add(bonusFood);
                }

                // spawn new obstacle every 5th time food is eaten
                if (score % 5 == 0) {
                    obstacles.add(new Obstacle(snake.getBody()));
                }
            }
        }

        food.addAll(newFoodList);

        if(!incLenght) {
            snake.getBody().remove(snake.getBody().size() - 1); // remove the tail to complete movement
        }
    }

    private void adjustSnakeSpeed(double speedMultiplier) {
        int delay = (int) (1000 / (FPS * Snake.SPEED * speedMultiplier));
        gameLoop.setDelay(delay);
    }

    public int getScore () {
        return this.score;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintComponent(g); // draw background first

        Graphics2D frame = (Graphics2D) g; // frame for drawing 2d graphics

        for (Food foodItem : food)
            foodItem.draw(frame);
        for (Obstacle obstacle : obstacles.getObstacles())
            obstacle.draw(frame);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Public Pixel", Font.PLAIN,20));
        g.drawString(String.format("%03d", score), 65 , GameFrame.WINDOW_SIZE.y - 760);


        snake.draw(frame);
        frame.dispose();
    }

    // starts the game loop
    public void startGame() {
        gameLoop.start();
    }

    public void stopGame() {
        gameLoop.stop();

        Player tempPlayer = new Player("", score);

        if (score > 0 && Leaderboard.isTopTen(tempPlayer)) {
            stateChanger.changeState(GameState.GAME_OVER_ENTERNAME);
        }
        else {
            stateChanger.changeState(GameState.GAME_OVER);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            snake.updateDirection(Direction.UP);
        }
        if (code == KeyEvent.VK_DOWN) {
            snake.updateDirection(Direction.DOWN);
        }
        if (code == KeyEvent.VK_LEFT) {
            snake.updateDirection(Direction.LEFT);
        }
        if (code == KeyEvent.VK_RIGHT) {
            snake.updateDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
