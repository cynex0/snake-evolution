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
        body.add(new CellPosition(17, 20));
        body.add(new CellPosition(16, 20));
        body.add(new CellPosition(15, 20));

        frameCount = 0;
        foodEaten = false;
    }

    public ArrayList<CellPosition> getBody() {
        return body;
    }

    public void increaseLength() {
        foodEaten = true;
    }
    
    private CellPosition calculateNextPos() {
        Point currHeadPos = body.get(0).getCell();
        CellPosition nextPos = null;
        
        switch (direction) {
            case UP -> nextPos = new CellPosition(currHeadPos.x, currHeadPos.y - 1);
            case DOWN -> nextPos = new CellPosition(currHeadPos.x, currHeadPos.y + 1);
            case RIGHT -> nextPos = new CellPosition(currHeadPos.x + 1, currHeadPos.y);
            case LEFT -> nextPos = new CellPosition(currHeadPos.x - 1, currHeadPos.y);
        }
        
        return nextPos;
    }
    
    private boolean doSelfCollision(CellPosition nextPos) {
        return body.subList(1, body.size())
                   .contains(nextPos);
    }

    private boolean doBorderCollision(CellPosition nextPos) {
        Point nextCoords = nextPos.getCoordinates();
        if ((nextCoords.x >= GameFrame.WINDOW_SIZE.x - BgPanel.MARGIN_INNER) || (nextCoords.x < BgPanel.MARGIN_INNER)) {
            return true;
        }
        if ((nextCoords.y >= GameFrame.WINDOW_SIZE.y - BgPanel.MARGIN_INNER) || (nextCoords.y < BgPanel.MARGIN_INNER)) {
            return true;
        }
        return false;
    }

    public boolean doCollisions() {
//        CellPosition nextPos = calculateNextPos();
        CellPosition headPos = body.get(0);
//        return doSelfCollision(nextPos) || doBorderCollision(nextPos);
        return doSelfCollision(headPos) || doBorderCollision(headPos);
    }

    public void move() {
        if (frameCount++ < ANIM_STEP) return;

        CellPosition newHeadPos = calculateNextPos();
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

    public boolean foodEaten(Food f) {
        if (body.get(0).getCoordinates().x == f.getFoodLocation().x) {
            if (body.get(0).getCoordinates().y == f.getFoodLocation().y)
                return true;
        }
        return false;
    }
}
