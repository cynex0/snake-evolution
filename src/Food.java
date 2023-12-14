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

    public void draw (Graphics g) {
        Point coords = foodLocation.getCoordinates();

        g.setColor(Color.BLUE);
        g.drawRect(coords.x, coords.y, FOOD_SIZE, FOOD_SIZE);
        g.setColor(Color.GRAY);
        g.fillRect(coords.x, coords.y, FOOD_SIZE, FOOD_SIZE);
    }

    public CellPosition getFoodLocation () {
        return this.foodLocation;
    }
}
