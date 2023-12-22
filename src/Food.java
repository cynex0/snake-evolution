import java.awt.*;
import java.util.Random;

public class Food {
    private static final int MIN_CELL = BgPanel.MARGIN_CELLS; // first playable cell
    private static final int MAX_CELL = GamePanel.CELL_COUNT - BgPanel.MARGIN_CELLS - 1; // last playable cell
    private static final int FOOD_SIZE = GamePanel.CELL_SIZE-2 ;

    private CellPosition foodLocation;
    private CellPosition bonusFoodLocation;
    private Random rand;
    private BonusFoodType currentFoodType;
    private Color bonusFoodColor;

    public Food () {
        foodLocation = new CellPosition();
        bonusFoodLocation = new CellPosition();
        rand = new Random();
        respawn();
        respawnBonusFood();
        bonusFoodColor = Color.blue;
        currentFoodType = BonusFoodType.SPEEDFOOD;
    }

    //generate a random BonusFoodType
    public void setBonusFoodType () {
        BonusFoodType newFoodType = BonusFoodType.SPEEDFOOD;
        int randInt = rand.nextInt(4) +1;
        switch (randInt) {
            case 1 -> {
                newFoodType = BonusFoodType.SPEEDFOOD;
                bonusFoodColor = Color.blue;
            }
            case 2 -> {
                newFoodType = BonusFoodType.SLOWFOOD;
                bonusFoodColor = Color.yellow;
            }
            case 3 -> {
                newFoodType = BonusFoodType.PLUSFOOD;
                bonusFoodColor = Color.green;
            }
            case 4 -> {
                newFoodType = BonusFoodType.MINUSFOOD;
                bonusFoodColor = Color.red;
            }
        }
        this.currentFoodType = newFoodType;
    }
    public void respawn() {
        int randX = rand.nextInt(MAX_CELL - MIN_CELL + 1) + MIN_CELL;
        int randY = rand.nextInt(MAX_CELL - MIN_CELL + 1) + MIN_CELL;
        foodLocation = new CellPosition(randX, randY);
    }
    public void respawnBonusFood () {
        int randX = rand.nextInt(MAX_CELL - MIN_CELL + 1) + MIN_CELL;
        int randY = rand.nextInt(MAX_CELL - MIN_CELL + 1) + MIN_CELL;
        bonusFoodLocation = new CellPosition(randX, randY);
    }

    public void draw (Graphics2D frame) {
        Point coords = foodLocation.getCoordinates(); // top left coords of the cell
        int halfCell = GamePanel.CELL_SIZE / 2;

        int[] xPoints = {coords.x, coords.x + halfCell, coords.x + 2*halfCell, coords.x + halfCell};
        int[] yPoints = {coords.y + halfCell, coords.y, coords.y + halfCell, coords.y + 2*halfCell};

        frame.setColor(new Color(0x2b331a));
        frame.fillPolygon(xPoints, yPoints,4); // draws a romb centered in the cell
    }



    public void drawBonusFood (Graphics2D frame) {
        Point coords = bonusFoodLocation.getCoordinates(); // top left coords of the cell
        frame.setColor(bonusFoodColor);
        frame.fillRect(coords.x, coords.y, GamePanel.CELL_SIZE, GamePanel.CELL_SIZE);
    }

    public CellPosition getFoodLocation () {
        return this.foodLocation;
    }
    public CellPosition getBonusFoodLocation () {
        return this.bonusFoodLocation;
    }

    public BonusFoodType getBonusFoodType() {
        return this.currentFoodType;
    }
}
