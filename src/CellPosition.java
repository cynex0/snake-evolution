import java.awt.*;

public class CellPosition extends Point {
    // x, y inherited - store cell index

    public CellPosition(){
        super(0, 0);
    }

    public CellPosition(int initCellX, int initCellY) {
        x = initCellX;
        y = initCellY;
    }

    public Point getCoordinates() { // returns coordinates of the current cell
        return new Point(x * GamePanel.CELL_SIZE, y * GamePanel.CELL_SIZE);
    }

    public Point getCellPoint() { return new Point(x, y); }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (!(object instanceof CellPosition other)) return false;

        return (this.x == other.x) &&
                (this.y == other.y);
    }
}
