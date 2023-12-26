package objects.food;

import java.awt.*;
import java.util.Random;

import panels.GamePanel;
import utilities.BgPanel;
import utilities.CellPosition;

public class Food {
    private static final int MIN_CELL = BgPanel.MARGIN_CELLS; // first playable cell
    private static final int MAX_CELL = GamePanel.CELL_COUNT - BgPanel.MARGIN_CELLS - 1; // last playable cell
    protected static final int BORDER_SIZE = 2;

    protected CellPosition foodLocation;
    protected Random rand;
    protected Color color;
    protected FoodType type;


    public Food () {
        foodLocation = new CellPosition();
        rand = new Random();
        color = new Color(0x2b331a);
        type = FoodType.DEFAULT;
        respawn();
    }

    public void respawn() {
        int randX = rand.nextInt(MAX_CELL - MIN_CELL + 1) + MIN_CELL;
        int randY = rand.nextInt(MAX_CELL - MIN_CELL + 1) + MIN_CELL;
        foodLocation = new CellPosition(randX, randY);
    }

    public void draw (Graphics2D frame) {
        Point coords = foodLocation.getCoordinates(); // top left coords of the cell
        int halfCell = GamePanel.CELL_SIZE / 2;
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

    public CellPosition getFoodLocation () {
        return this.foodLocation;
    }

    public FoodType getFoodType() {
        return this.type;
    }
}
