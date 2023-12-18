import java.awt.*;
import java.util.Random;

public class Food {
    private static final int MIN_CELL = BgPanel.MARGIN_CELLS; // first playable cell
    private static final int MAX_CELL = GamePanel.CELL_COUNT - BgPanel.MARGIN_CELLS - 1; // last playable cell
    private static final int FOOD_SIZE = GamePanel.CELL_SIZE-2 ;

    private CellPosition foodLocation;
    private Random rand;

    public Food (){
        foodLocation = new CellPosition();
        rand = new Random();
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

        int[] xPoints = {coords.x, coords.x + halfCell, coords.x + 2*halfCell, coords.x + halfCell};
        int[] yPoints = {coords.y + halfCell, coords.y, coords.y + halfCell, coords.y + 2*halfCell};

        frame.setColor(new Color(0x2b331a));
        frame.fillPolygon(xPoints, yPoints,4); // draws a romb centered in the cell
    }
/*
    public void drawSpeedFood (Graphics2D frame) {
        Point coords = foodLocation.getCoordinates(); // top left coords of the cell
        int halfCell = GamePanel.CELL_SIZE / 2;

        int[] xPoints = {coords.x, coords.x + halfCell, coords.x + 2*halfCell, coords.x + halfCell};
        int[] yPoints = {coords.y + halfCell, coords.y, coords.y + halfCell, coords.y + 2*halfCell};

        frame.setColor(Color.blue);
        frame.fillRect(xPoints, yPoints,4, 4); // draws a romb centered in the cell
    }

 */

    public CellPosition getFoodLocation () {
        return this.foodLocation;
    }
}
