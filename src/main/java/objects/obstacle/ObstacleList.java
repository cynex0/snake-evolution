package objects.obstacle;

import utilities.CellPosition;

import java.util.ArrayList;

/**
 * Represents a List of all existing obstacles in the game.
 * @author Maksims Orlovs
 */
public class ObstacleList {
    private ArrayList<Obstacle> obstacles;

    /**
     * Constructs the ObstacleList with an empty ArrayList of Obstacle.
     * @author Maksims Orlovs
     */
    public ObstacleList() {
        obstacles = new ArrayList<>();
    }

    /**
     * Returns a sum of positions of all obstacles.
     * @return An ArrayList of CellPosition:s that represents a list of all cells occupied by all obstacles in the list.
     * @author Maksims Orlovs
     */
    public ArrayList<CellPosition> getAllCells() {
        ArrayList<CellPosition> cells = new ArrayList<>();
        for (Obstacle obstacle : obstacles) {
            cells.addAll(obstacle.getCells());
        }
        return cells;
    }

    /**
     * A setter to add an Obstacle to the list.
     * @param obstacle new Obstacle to be added to the list
     * @author Maksims Orlovs
     */
    public void add(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * A getter for the list of obstacles.
     * @return returns a list of all currently present Obstacles
     * @author Maksims Orlovs
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
