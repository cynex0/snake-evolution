package org.panels;

import org.objects.*;
import org.objects.food.*;
import org.objects.obstacle.*;
import org.engine.*;
import org.utilities.CellPosition;
import org.utilities.Direction;
import org.utilities.GameConstants;
import org.utilities.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * Represents the gameplay state. Handles gameplay logic and drawing of all objects.
 * @author Maksims Orlovs
 * @author Fatemeh Akbarifar (co-author)
 */
public class GamePanel extends JPanel implements KeyListener {
    private BgPanel bg;
    private Snake snake;
    private ArrayList<Food> food;
    private Random rand;
    private ObstacleList obstacles;

    private int score;
    private final Timer gameLoop;
    private long startTime;
    String currentEffectLabel;
    private boolean fastMode, slowMode, keyInverter;
    private StateChangeListener stateChanger;

    /**
     * Constructs the initial GamePanel and objects for handling the gameplay.
     * @param listener reference to the observer object to allow requesting state change
     * @author Maksims Orlovs
     * @author Fatemeh Akbarifar (co-author)
     */
    public GamePanel(StateChangeListener listener) {
        super();
        this.setPreferredSize(new Dimension(GameConstants.WINDOW_SIZE.x, GameConstants.WINDOW_SIZE.y));
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

        int delay = (int) (1000 / (GameConstants.FPS * Snake.SPEED));
        gameLoop = new Timer(delay, e -> { // GAME LOOP, runs every 1/60*SPEED -th of a second

            //1000/(int)(FPS * objects.Snake.SPEED)
            update();
            repaint(); // calls paintComponent()

        });
    }

    /**
     * Updates positions and interaction of all objects (snake effects, position, collision). Part of game loop.
     * @author Maksims Orlovs
     * @author Fatemeh Akbarifar (co-author)
     */
    public void update() {
        updateEffects();
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
        boolean incLength = doFoodCollisions();
        // do not remove tail for a length-increase effect
        if(!incLength) {
            snake.getBody().remove(snake.getBody().size() - 1); // remove the tail to complete movement
        }
    }

    /**
     * Adjusts the delay between frames in the game loop. Appears as the game's speed change.
     * @param speedMultiplier factor to multiply the speed by.
     * @author Fatemeh Akbarifar
     */
    private void adjustSnakeSpeed(double speedMultiplier) {
        int delay = (int) (1000 / (GameConstants.FPS * Snake.SPEED * speedMultiplier));
        gameLoop.setDelay(delay);
    }

    /**
     * Updates the status of the effects. Check if the effect time is over, removes the effect.
     * @author Fatemeh Akbarifar
     */
    private void updateEffects() {
        if (fastMode || slowMode || keyInverter) {
            // check if effect time expired
            if (System.currentTimeMillis() - startTime > GameConstants.EFFECT_DURATION) {
                fastMode = false;
                slowMode = false;
                keyInverter = false;
                adjustSnakeSpeed(1); // Set the speed back to normal
                currentEffectLabel = ""; // remove the label
            }
        }
    }

    /**
     * Checks collisions with food items
     * @return true if food is eaten
     * @author Fatemeh Akbarifar
     * @author Maksims Orlovs (co-author)
     */
    private boolean doFoodCollisions() {
        boolean eaten = false;
        Iterator<Food> it = food.iterator();
        ArrayList<Food> newFood = new ArrayList<>();

        while (it.hasNext()) {
            Food foodItem = it.next();
            if (snake.checkCollisionWith(foodItem.getFoodLocation())) {
                it.remove(); // remove consumed food from list
                eaten = true;
                applyFoodEffect(foodItem.getFoodType());

                // always spawn new default food as soon as the previous one is eaten
                if(foodItem.getFoodType() == FoodType.DEFAULT)
                    newFood.add(generateNewFoodItem(false));

                // 33% to spawn new bonus food in a valid position, up to 1 normal and 2 bonus
                if (food.size() < 2 && rand.nextFloat() <= 0.33)
                    newFood.add(generateNewFoodItem(true));

                // spawn new obstacle every 5th time food is eaten
                if (score % 5 == 0)
                    obstacles.add(new Obstacle(snake.getBody()));
            }
        }

        food.addAll(newFood); // add all generated food from the temporary storage to the actual list
        return eaten;
    }

    /**
     * Applies the effect of an eaten food object to the game depending on the type of the food.
     * @param foodType the type of the eaten food
     * @author Fatemeh Akbarifar
     * @author Maksims Orlovs (co-author)
     * @author Marwa Abohachem (co-author)
     */
    private void applyFoodEffect(FoodType foodType) {
        switch (foodType) {
            case DEFAULT -> {
                score++;
            }
            case SPEEDFOOD -> {
                fastMode = true;
                adjustSnakeSpeed(2);
                currentEffectLabel = "SPED UP!";
                startTime = System.currentTimeMillis();
            }
            case SLOWFOOD -> {
                slowMode = true;
                adjustSnakeSpeed(0.5);
                currentEffectLabel = "SLOWED!";
                startTime = System.currentTimeMillis();
            }
            case PLUSFOOD -> {
                score += 2;
            }
            case MINUSFOOD -> {
                score -= 2;
                if (score < 0) score = 0;
            }
            case CONTROLINVERTER -> {
                keyInverter = true;
                startTime = System.currentTimeMillis();
                adjustSnakeSpeed(0.75);
                currentEffectLabel = "CONFUSED!";
            }
        }
    }

    /**
     * Generates a food object in a random valid position.
     * @param isBonus determines if a food item to generate is default or bonus
     * @return generated Food object
     * @author Maksims Orlovs
     */
    private Food generateNewFoodItem(boolean isBonus) {
        CellPosition newFoodPos;
        Food newFood;

        // respawn food until it's in a valid position
        do {
            if (!isBonus) newFood = new Food();
            else newFood = new BonusFood();
            newFoodPos = newFood.getFoodLocation();
        } while (snake.checkCollisionWith(newFoodPos) || obstacles.getAllCells().contains(newFoodPos));

        return newFood;
    }

    /**
     * Getter for the current player score.
     * @return current score
     * @author Fatemeh Akbarifar
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Draws all contents of the panel (snake, food objects, obstacles, score) and the background.
     * @param g graphics component supplied by the GameFrame
     * @author Maksims Orlovs
     * @author Fatemeh Akbarifar (co-author)
     * @author Marwa Abohachem (co-author)
     */
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
        g.drawString(String.format("%03d", score), 65 , GameConstants.WINDOW_SIZE.y - 760);

        if (currentEffectLabel != null && !currentEffectLabel.isEmpty()) {
            frame.setColor(Color.BLACK);
            frame.setFont(new Font("Public Pixel", Font.BOLD, 20));

            int x = (getWidth() - frame.getFontMetrics().stringWidth(currentEffectLabel)) / 2;
            int y = getHeight() - frame.getFontMetrics().getHeight() - 2; // centers the effect label 2 pixels from the bottom

            frame.drawString(currentEffectLabel, x, y);
        }

        snake.draw(frame);
        frame.dispose();
    }

    /**
     * Starts the game loop.
     * @author Maksims Orlovs
     */
    public void startGame() {
        gameLoop.start();
    }

    /**
     * Ends the game loop and changes to the appropriate game-over screen.
     * @author Fatemeh Akbarifar
     */
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

    /**
     * Handles user input. Requests the change of direction from the snake if one of the arrow keys is pressed.
     * @param e the key-press event to be processed
     * @author Fatemeh Akbarifar
     * @author Maksims Orlovs (co-author)
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            if(keyInverter) {
                snake.updateDirection(Direction.DOWN);
            }
            else {
                snake.updateDirection(Direction.UP);
            }
        }
        if (code == KeyEvent.VK_DOWN) {
            if(keyInverter) {
                snake.updateDirection(Direction.UP);
            }
            else {
                snake.updateDirection(Direction.DOWN);
            }
        }
        if (code == KeyEvent.VK_LEFT) {
            if(keyInverter) {
                snake.updateDirection(Direction.RIGHT);
            }
            else {
                snake.updateDirection(Direction.LEFT);
            }
        }
        if (code == KeyEvent.VK_RIGHT) {
            if(keyInverter) {
                snake.updateDirection(Direction.LEFT);
            }
            else {
                snake.updateDirection(Direction.RIGHT);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
