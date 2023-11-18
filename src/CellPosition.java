import java.awt.*;

public class CellPosition extends Point {
    // x, y inherited
    // x, y store actual coordinates in pixels
    private final int cellsX;
    private final int cellsY;

    public CellPosition(){
        super(0, 0);
        this.cellsX = 0;
        this.cellsY = 0;
    }

    public CellPosition(int cellsX, int cellsY) {
        super(0, 0);
        this.cellsX = cellsX;
        this.cellsY = cellsY;
    }

    public CellPosition(int initCellX, int initCellY, int cellsX, int cellsY) {
        super(initCellX * GamePanel.CELL_SIZE, initCellY * GamePanel.CELL_SIZE);
        this.cellsX = cellsX;
        this.cellsY = cellsY;
    }

    public Point getCell() { // returns cell indexes corresponding to current position
        return new Point(x/cellsX, y/cellsY);
    }

    public Point getCoordinates() { // returns coordinates of the current position
        return new Point(x, y);
    }
}
