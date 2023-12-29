package utilities;

import java.awt.*;


/**
 * Represents the position on screen in cell-system. Stores x and y index of the cell.
 * @author Maksims Orlovs
 */
public class CellPosition extends Point {
    // x,y - inherited
    /**
     * Creates the CellPosition object with default cell 0,0
     * @author Maksims Orlovs
     */
    public CellPosition(){
        super(0, 0);
    }

    /**
     * Creates the CellPosition with supplied indexes.
     * @param initCellX cell x index
     * @param initCellY cell y index
     * @author Maksims Orlovs
     */
    public CellPosition(int initCellX, int initCellY) {
        x = initCellX;
        y = initCellY;
    }

    /**
     * Converts the cell indexes into top-left pixel coordinates of the cell.
     * @return Point representing the position of the top-left corner of the cell in pixels.
     * @author Maksims Orlovs
     */
    public Point getCoordinates() { // returns coordinates of the current cell
        return new Point(x * GameConstants.CELL_SIZE, y * GameConstants.CELL_SIZE);
    }

    /**
     * Compares the CellPosition to another object.
     * @param object an object to be compared with this object
     * @return true if x and y indexes of both objects are equal
     * @author Maksims Orlovs
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (!(object instanceof CellPosition other)) return false;

        return (this.x == other.x) &&
                (this.y == other.y);
    }
}
