package objects;

import utilities.CellPosition;
import utilities.Direction;
import utilities.GameConstants;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represents a snake object
 * @author Maksims Orlovs
 */
public class Snake {
    public static final double SPEED = 0.18; // FPS multiplier
    public static final int INIT_LEN = 5; // FPS multiplier

    private ArrayList<CellPosition> body;
    private Direction currentDirection;
    private LinkedList<Direction> inputQueue;

    /**
     * Constructs a Snake object in the middle of the screen. Initial parameters: length 5, direction right.
     * @author Maksims Orlovs
     */
    public Snake() {
        body = new ArrayList<>();
        currentDirection = Direction.RIGHT;
        inputQueue = new LinkedList<>();

        // body elements starting from the middle of the screen and "growing" to the left
        int xPos = GameConstants.CELL_COUNT / 2;
        for (int i = 0; i < INIT_LEN; i++)
            body.add(new CellPosition(xPos--, GameConstants.CELL_COUNT / 2));
    }

    /**
     * Getter for the current snake position.
     * @return an ArrayList of CellPosition:s representing all cells occupied by the Snake body.
     * @author Maksims Orlovs
     */
    public ArrayList<CellPosition> getBody() {
        return body;
    }

    /**
     * Helper method for calculating the position of the head in after applying the movement.
     * @return a CellPosition representing the cell that the Snake's head is to be moved to.
     * @author Maksims Orlovs
     */
    private CellPosition calculateNextPos() {
        CellPosition currHeadPos = body.get(0);
        CellPosition nextPos = null;
        
        switch (currentDirection) {
            case UP -> nextPos = new CellPosition(currHeadPos.x, currHeadPos.y - 1);
            case DOWN -> nextPos = new CellPosition(currHeadPos.x, currHeadPos.y + 1);
            case RIGHT -> nextPos = new CellPosition(currHeadPos.x + 1, currHeadPos.y);
            case LEFT -> nextPos = new CellPosition(currHeadPos.x - 1, currHeadPos.y);
        }
        
        return nextPos;
    }

    /**
     * Helper method that checks if the snake collided with itself.
     * @param head the CellPosition representing the Snake's head position
     * @return true if snake's head collided with its body
     * @author Maksims Orlovs
     */
    private boolean doSelfCollision(CellPosition head) {
        return body.subList(1, body.size())
                   .contains(head);
    }

    /**
     * Helper method that checks if the snake collided with itself.
     * @param head the CellPosition representing the Snake's head position
     * @return true if snake's head collided with one of the borders
     * @author Fatemeh Akbarifar
     */
    private boolean doBorderCollision(CellPosition head) {
        Point nextCoords = head.getCoordinates();
        if ((nextCoords.x >= GameConstants.WINDOW_SIZE.x - GameConstants.MARGIN_INNER) || (nextCoords.x < GameConstants.MARGIN_INNER)) {
            return true;
        }
        if ((nextCoords.y >= GameConstants.WINDOW_SIZE.y - GameConstants.MARGIN_INNER) || (nextCoords.y < GameConstants.MARGIN_INNER)) {
            return true;
        }
        return false;
    }

    /**
     * Checks snake's collision with self and borders
     * @return true if the snake collided with self or one of the borders.
     * @see Snake#doSelfCollision(CellPosition)
     * @see Snake#doBorderCollision(CellPosition)
     * @author Maksims Orlovs
     */
    public boolean doCollisions() {
        CellPosition headPos = body.get(0);
        return doSelfCollision(headPos) || doBorderCollision(headPos);
    }

    /**
     * Updates the position of the head depending on the direction input. Adds the new head to the snake body.
     * @see Snake#calculateNextPos()
     * @author Maksims Orlovs
     */
    public void move() {
        if (!inputQueue.isEmpty())
            currentDirection = inputQueue.poll(); // change direction if any inputs queued

        CellPosition newHeadPos = calculateNextPos();
        body.add(0, newHeadPos);
    }

    /**
     * Adds new input to the input queue if less than 2 inputs are queued and if is not opposite to the previously
     * queued input.
     * @param newDir new direction requested from the user (from the keyboard input in the engine)
     * @author Maksims Orlovs
     */
    public void updateDirection(Direction newDir) {
        if (inputQueue.size() < 2 && !isOppositeDir(inputQueue.peekLast(), newDir))
            inputQueue.add(newDir);

        if (!inputQueue.isEmpty() && isOppositeDir(inputQueue.peek(), currentDirection))
            inputQueue.removeFirst(); // drops the next direction in queue if it is opposite to the current direction
    }

    /**
     * Checks if two supplied directions are opposite to each other
     * @param dir1 Direction 1
     * @param dir2 Direction 2
     * @return true if the directions are opposite
     * @author Maksims Orlovs
     */
    private boolean isOppositeDir(Direction dir1, Direction dir2) {
        return (dir1 == Direction.DOWN && dir2 == Direction.UP) ||
               (dir1 == Direction.UP && dir2 == Direction.DOWN) ||
               (dir1 == Direction.LEFT && dir2 == Direction.RIGHT) ||
               (dir1 == Direction.RIGHT && dir2 == Direction.LEFT);
    }

    /**
     * Method to draw the snake object onto the screen (frame).
     * @param frame Swing Graphics2D object that represents the current frame to be updated.
     * @author Maksims Orlovs
     */
    public void draw(Graphics2D frame) {
        frame.setColor(new Color(0x2b331a));
        for (CellPosition pos : body) {
            Point p = pos.getCoordinates();
            frame.fillRect(p.x, p.y, GameConstants.CELL_SIZE, GameConstants.CELL_SIZE);
        }
    }

    /**
     * Checks if snake collides with any object at given position.
     * @param pos a CellPosition of an object to check collision with.
     * @return true if the snake collides with the object.
     * @author Maksims Orlovs
     */
    public boolean checkCollisionWith(CellPosition pos) {
        return body.contains(pos);
    }

    /**
     * Checks if snake collides with a multi-cell object at given position. Overload to support multi-cell objects.
     * @param pos an ArrayList of CellPosition:s that represents a multi-cell object to check collision with.
     * @return true if the snake collides with any cell of the multi-cell object.
     * @author Maksims Orlovs
     */
    public boolean checkCollisionWith(ArrayList<CellPosition> pos) {
        if (pos.isEmpty()) return false;

        for (CellPosition p : pos) {
            if (body.contains(p)) return true;
        }
        return false;
    }
}
