import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class Snake {
    private final int ANIM_STEP = 3; // updates every Nth frame

    private int frameCount;
    private ArrayList<CellPosition> body;
    private Direction direction;

    public Snake() {
        super();

        body = new ArrayList<>();
        direction = Direction.RIGHT;
        body.add(new CellPosition(20, 20));
        body.add(new CellPosition(19, 20));
        body.add(new CellPosition(18, 20));

        frameCount = 0;
    }

    public ArrayList<CellPosition> getBody() {
        return body;
    }

    public void increaseBodyLength() {
        Point tailCellPos = body.get(body.size() - 1).getCellPoint();
        CellPosition newPos = new CellPosition();

        switch (direction) {
            case UP -> newPos = new CellPosition(tailCellPos.x, tailCellPos.y + 1);
            case DOWN -> newPos = new CellPosition(tailCellPos.x, tailCellPos.y - 1);
            case RIGHT -> newPos = new CellPosition(tailCellPos.x - 1, tailCellPos.y);
            case LEFT -> newPos = new CellPosition(tailCellPos.x + 1, tailCellPos.y);
        }

        body.add(newPos);
    }

    public void move() {
        if (frameCount++ < ANIM_STEP) return;

        CellPosition newHeadPos = new CellPosition();
        Point currHeadPos = body.get(0).getCellPoint();

        switch (direction) {
            case UP -> newHeadPos = new CellPosition(currHeadPos.x, currHeadPos.y - 1);
            case DOWN -> newHeadPos = new CellPosition(currHeadPos.x, currHeadPos.y + 1);
            case RIGHT -> newHeadPos = new CellPosition(currHeadPos.x + 1, currHeadPos.y);
            case LEFT -> newHeadPos = new CellPosition(currHeadPos.x - 1, currHeadPos.y);
        }

        body.add(0, newHeadPos);
        body.remove(body.size() - 1);

        frameCount = 0;
    }

    public void setDirection(Direction newDir) {
        direction = newDir;
    }
}
