import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Obstacle {
    private ArrayList<CellPosition> cells;
    private Random rand;
    private static final int MIN_CELL = BgPanel.MARGIN_CELLS + 1; // first playable cell + 1
    private static final int MAX_CELL = GamePanel.CELL_COUNT - BgPanel.MARGIN_CELLS - 2; // last playable cell - 1
    private static final int MAX_SIZE = 5; // maximum amt of cells in an obstacle

    public Obstacle(ArrayList<CellPosition> snakePos) {
        rand = new Random();
        respawn(snakePos);
    }

    public void respawn(ArrayList<CellPosition> snakePos) {
        CellPosition startPos = getRandomCell();
        cells.add(startPos);
    }

    private CellPosition getRandomCell() {
        int randX = rand.nextInt(MAX_CELL - MIN_CELL + 1) + MIN_CELL;
        int randY = rand.nextInt(MAX_CELL - MIN_CELL + 1) + MIN_CELL;
        return new CellPosition(randX, randY);
    }

    public void draw(Graphics2D frame) {
        frame.setColor(Color.BLACK);
        for (CellPosition pos : cells) {
            Point p = pos.getCoordinates();
            frame.fillRect(p.x, p.y, GamePanel.CELL_SIZE, GamePanel.CELL_SIZE);
        }
    }
}
