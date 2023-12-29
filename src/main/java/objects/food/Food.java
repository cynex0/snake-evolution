package objects.food;

import java.awt.*;
import java.util.Random;

import utilities.CellPosition;
import utilities.GameConstants;


/**
 * Represents a food item.
 * @author Fatemeh Akbarifar
 */
public class Food {
    protected static final int BORDER_SIZE = 2; // thickness of the border of the food item

    protected CellPosition foodLocation;
    protected Random rand;
    protected Color color;
    protected FoodType type;

    /**
     * Creates a food item of default type in a random playable cell.
     * @author Fatemeh Akbarifar
     * @author Maksims Orlovs (co-author)
     */
    public Food() {
        foodLocation = new CellPosition();
        rand = new Random();
        color = new Color(0x2b331a);
        type = FoodType.DEFAULT;
        respawn();
    }

    /**
     * Sets the position to a random playable cell.
     * @author Fatemeh Akbarifar
     */
    public void respawn() {
        int randX = rand.nextInt(GameConstants.MAX_CELL - GameConstants.MIN_CELL + 1) + GameConstants.MIN_CELL;
        int randY = rand.nextInt(GameConstants.MAX_CELL - GameConstants.MIN_CELL + 1) + GameConstants.MIN_CELL;
        foodLocation = new CellPosition(randX, randY);
    }

    /**
     * Draws the food object onto the supplied frame in its current position.
     * @param frame Swing Graphics2D object that represents the current frame to be updated
     * @author Fatemeh Akbarifar
     */
    public void draw (Graphics2D frame) {
        Point coords = foodLocation.getCoordinates(); // top left coords of the cell
        int halfCell = GameConstants.CELL_SIZE / 2;
        int[] xPoints, yPoints; // romb point coordinates, clockwise, starting from left (9 o'clock) corner

        // draw border
        frame.setColor(Color.BLACK);
        xPoints = new int[]{coords.x - BORDER_SIZE, coords.x + halfCell, coords.x + 2*halfCell + BORDER_SIZE, coords.x + halfCell};
        yPoints = new int[]{coords.y + halfCell, coords.y - BORDER_SIZE, coords.y + halfCell, coords.y + 2*halfCell + BORDER_SIZE};
        frame.fillPolygon(xPoints, yPoints,4);

        // draw colored middle
        frame.setColor(color);
        xPoints = new int[]{coords.x, coords.x + halfCell, coords.x + 2*halfCell, coords.x + halfCell};
        yPoints = new int[]{coords.y + halfCell, coords.y, coords.y + halfCell, coords.y + 2*halfCell};
        frame.fillPolygon(xPoints, yPoints,4);
    }

    /**
     * Getter for the current food position.
     * @return CellPosition representing the current position
     * @author Fatemeh Akbarifar
     */
    public CellPosition getFoodLocation () {
        return this.foodLocation;
    }

    /**
     * Getter for the food type.
     * @return FoodType of the food item
     * @author Fatemeh Akbarifar
     */
    public FoodType getFoodType() {
        return this.type;
    }
}
