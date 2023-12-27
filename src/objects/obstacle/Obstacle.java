package objects.obstacle;

import objects.food.Food;
import utilities.CellPosition;
import utilities.GameConstants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an obstacle.
 * @author Maksims Orlovs
 */
public class Obstacle {
    private static final int MAX_SIZE = 5; // maximum amt of cells in an obstacle
    private static final int PARTICLE_COUNT = 8;
    private static final int PARTICLE_SIZE = GameConstants.CELL_SIZE / 5;

    private ArrayList<CellPosition> cells;
    private Random rand;

    /**
     * Creates and spawnds an obstacle object.
     * @author Maksims Orlovs
     * @param snakePos snake position at the time of obstacle spawning to prevent incorrect spawning position.
     * @see Obstacle#respawn(ArrayList)
     */
    public Obstacle(ArrayList<CellPosition> snakePos) {
        rand = new Random();
        cells = new ArrayList<>();
        respawn(snakePos);
    }

    /**
     * Spawns the obstacle of a random size and shape on the playing field, in a valid position.
     * Spawning stops when an incorrect position has been generated.
     * @param snakePos snake position at the time of obstacle spawning to prevent spawning in the snake.
     * @author Maksims Orlovs
     */
    public void respawn(ArrayList<CellPosition> snakePos) {
        CellPosition startPos = getRandomCell(); // gets a random cell to start the spawning process
        if (snakePos.contains(startPos)) return; // prevent spawning if started spawning inside of the snake

        cells.add(startPos); // add the starting cell to the list of obstacle cells

        double growChance = 0.75; // chance for increasing the number of cells
        // generate additional cells, up to MAX_SIZE, with diminishing probability
        for (int i = 0; i < MAX_SIZE; i++) {
            double roll = rand.nextDouble(); // random number to determine if the obstacle should grow more

            if (roll <= growChance) {
                // grow in a random direction
                ArrayList<CellPosition> viableCells = new ArrayList<>();
                CellPosition prevCell = cells.get(i);

                if (prevCell.x + 1 <= GameConstants.MAX_CELL) // can grow to the right
                    viableCells.add(new CellPosition(prevCell.x + 1, prevCell.y));
                if (prevCell.x - 1 >= GameConstants.MIN_CELL) // can grow to the left
                    viableCells.add(new CellPosition(prevCell.x - 1, prevCell.y));
                if (prevCell.y + 1 <= GameConstants.MAX_CELL) // can grow down
                    viableCells.add(new CellPosition(prevCell.x, prevCell.y + 1));
                if (prevCell.y - 1 >= GameConstants.MIN_CELL) // can grow up
                    viableCells.add(new CellPosition(prevCell.x, prevCell.y - 1));

                if (viableCells.isEmpty()) // stop spawning if no viable cells found
                    break;

                // choose a random cell out of the possible cells
                CellPosition nextCell = viableCells.get(rand.nextInt(viableCells.size()));
                if (snakePos.contains(nextCell)) // stop spawning if spawned inside the snake
                    break;

                cells.add(nextCell);
                growChance = growChance - 0.1; // add non-linearity to the chance
            }
            else break;
        }
    }

    /**
     * Generates a random cell within the playable area (excludes margins)
     * @return CellPosition representing a traversable cell
     * @author Fatemeh Akbarifar
     * @author Maksims Orlovs
     */
    private CellPosition getRandomCell() {
        int randX = rand.nextInt(GameConstants.MAX_CELL - GameConstants.MIN_CELL + 1) + GameConstants.MIN_CELL;
        int randY = rand.nextInt(GameConstants.MAX_CELL - GameConstants.MIN_CELL + 1) + GameConstants.MIN_CELL;
        return new CellPosition(randX, randY);
    }

    /**
     * Method to draw the obstacle item onto the screen (frame). Draws squares in obstacle positions and random "noise"
     * inside of them.
     * @param frame Swing Graphics2D object that represents the current frame to be updated.
     * @author Maksims Orlovs
     */
    public void draw(Graphics2D frame) {
        for (CellPosition pos : cells) {
            Point p = pos.getCoordinates();

            // draw the obstacle
            frame.setColor(Color.BLACK);
            frame.fillRect(p.x, p.y, GameConstants.CELL_SIZE, GameConstants.CELL_SIZE);

            // draw the "noise" in the obstacle
            for (int i = 0; i < PARTICLE_COUNT; i++) {
                if (rand.nextFloat() > 0.25)
                    frame.setColor(Color.DARK_GRAY);
                else
                    frame.setColor(Color.GRAY);

                int x = (int) (rand.nextFloat() * (GameConstants.CELL_SIZE - PARTICLE_SIZE)) + p.x;
                int y = (int) (rand.nextFloat() * (GameConstants.CELL_SIZE - PARTICLE_SIZE)) + p.y;
                frame.fillRect(x, y, PARTICLE_SIZE, PARTICLE_SIZE);
            }
        }
    }

    /**
     * Getter for the total obstacle position.
     * @return ArrayList of CellPosition:s that represent the cells occupied by the obstacle.
     * @author Maksims Orlovs
     */
    public ArrayList<CellPosition> getCells() {
        return cells;
    }
}
