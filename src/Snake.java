import java.awt.*;
import java.util.ArrayList;

public class
Snake {
    public static final double SPEED = 0.15; // FPS multiplier

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

        foodEaten = false;
    }

    public ArrayList<CellPosition> getBody() {
        return body;
    }

    public void increaseLength() {
        foodEaten = true;
    }
    
    private CellPosition calculateNextPos() {
        CellPosition currHeadPos = body.get(0);
        CellPosition nextPos = null;
        
        switch (direction) {
            case UP -> nextPos = new CellPosition(currHeadPos.x, currHeadPos.y - 1);
            case DOWN -> nextPos = new CellPosition(currHeadPos.x, currHeadPos.y + 1);
            case RIGHT -> nextPos = new CellPosition(currHeadPos.x + 1, currHeadPos.y);
            case LEFT -> nextPos = new CellPosition(currHeadPos.x - 1, currHeadPos.y);
        }
        
        return nextPos;
    }
    
    private boolean doSelfCollision(CellPosition pos) {
        return body.subList(1, body.size())
                   .contains(pos);
    }

    private boolean doBorderCollision(CellPosition pos) {
        Point nextCoords = pos.getCoordinates();
        if ((nextCoords.x >= GameFrame.WINDOW_SIZE.x - BgPanel.MARGIN_INNER) || (nextCoords.x < BgPanel.MARGIN_INNER)) {
            return true;
        }
        if ((nextCoords.y >= GameFrame.WINDOW_SIZE.y - BgPanel.MARGIN_INNER) || (nextCoords.y < BgPanel.MARGIN_INNER)) {
            return true;
        }
        return false;
    }

    public boolean doCollisions() {
        CellPosition headPos = body.get(0);
        return doSelfCollision(headPos) || doBorderCollision(headPos);
    }

    public void move() {
        CellPosition newHeadPos = calculateNextPos();
        body.add(0, newHeadPos);

        if (!foodEaten)
            body.remove(body.size() - 1);
        else
            foodEaten = false;
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
        Point headCoords = body.get(0).getCoordinates();
        if (headCoords.x == f.getFoodLocation().x) {
            if (headCoords.y == f.getFoodLocation().y)
                return true;
        }
        return false;
    }
}
