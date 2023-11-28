import java.awt.*;
import java.util.ArrayList;

public class
Snake {
    private final int ANIM_STEP = 3; // updates every Nth frame

    private int frameCount;
    private ArrayList<CellPosition> body;
    private Direction direction;
    private boolean foodEaten;

    public Snake() {
        super();

        body = new ArrayList<>();
        direction = Direction.RIGHT;
        body.add(new CellPosition(20, 20));
        body.add(new CellPosition(19, 20));
        body.add(new CellPosition(18, 20));

        frameCount = 0;
        foodEaten = false;
    }

    public ArrayList<CellPosition> getBody() {
        return body;
    }

    public void increaseLength() {
        foodEaten = true;
    }

    public void move() {
        if (frameCount++ < ANIM_STEP) return;

        CellPosition newHeadPos = new CellPosition();
        Point currHeadPos = body.get(0).getCell();

        switch (direction) {
            case UP -> newHeadPos = new CellPosition(currHeadPos.x, currHeadPos.y - 1);
            case DOWN -> newHeadPos = new CellPosition(currHeadPos.x, currHeadPos.y + 1);
            case RIGHT -> newHeadPos = new CellPosition(currHeadPos.x + 1, currHeadPos.y);
            case LEFT -> newHeadPos = new CellPosition(currHeadPos.x - 1, currHeadPos.y);
        }

        body.add(0, newHeadPos);
        if (!foodEaten)
            body.remove(body.size() - 1);
        else
            foodEaten = false;

        frameCount = 0;
    }

    public void setDirection(Direction newDir) {
        direction = newDir;
    }

    public Direction getDirection() {
        return direction;
    }

    public void draw(Graphics2D frame) {
        frame.setColor(new Color(0x2b331a));
        for (CellPosition pos : body) {
            Point p = pos.getCoordinates();
            frame.fillRect(p.x, p.y, GamePanel.CELL_SIZE, GamePanel.CELL_SIZE);
        }
    }
}
