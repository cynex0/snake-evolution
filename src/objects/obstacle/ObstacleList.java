package objects.obstacle;

import utilities.CellPosition;

import java.util.ArrayList;

public class ObstacleList {
    private ArrayList<Obstacle> obstacles;

    public ObstacleList() {
        obstacles = new ArrayList<>();
    }

    public ArrayList<CellPosition> getAllCells() {
        ArrayList<CellPosition> cells = new ArrayList<>();
        for (Obstacle obstacle : obstacles) {
            cells.addAll(obstacle.getCells());
        }
        return cells;
    }

    public void add(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}
