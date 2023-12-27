package objects.obstacle;

import utilities.CellPosition;

import java.util.ArrayList;

/**
 * Represents a List of all existing obstacles in the game.
 */
public class ObstacleList {
    private ArrayList<Obstacle> obstacles;

    /**
     * Constructs the ObstacleList with an empty ArrayList of Obstacle.
     */
    public ObstacleList() {
        obstacles = new ArrayList<>();
    }

    /**
     * Returns a sum of positions of all obstacles.
     * @return An ArrayList of CellPosition:s that represents a list of all cells occupied by all obstacles in the list.
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
     */
    public void add(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * A getter for the list of obstacles.
     * @return returns a list of all currently present Obstacles
     */
    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
