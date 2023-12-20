import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Snake {
    public static final double SPEED = 0.18; // FPS multiplier

    private ArrayList<CellPosition> body;
    private Direction currentDirection;
    private LinkedList<Direction> inputQueue;

    public Snake() {
        body = new ArrayList<>();
        currentDirection = Direction.RIGHT;
        inputQueue = new LinkedList<>();

        body.add(new CellPosition(20, 20));
        body.add(new CellPosition(19, 20));
        body.add(new CellPosition(18, 20));
        body.add(new CellPosition(17, 20));
        body.add(new CellPosition(16, 20));
        body.add(new CellPosition(15, 20));
    }

    public ArrayList<CellPosition> getBody() {
        return body;
    }
    
    private CellPosition calculateNextPos() {
        CellPosition currHeadPos = body.get(0);
        CellPosition nextPos = null;
        
        switch (currentDirection) {
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
        if (!inputQueue.isEmpty())
            currentDirection = inputQueue.poll();

        CellPosition newHeadPos = calculateNextPos();
        body.add(0, newHeadPos);
    }

    public void updateDirection(Direction newDir) {
        // adds new input to the queue,
        // if less than 2 inputs are queued and if is not opposite to the previously queued input
        if (inputQueue.size() < 2 && !isOppositeDir(inputQueue.peekLast(), newDir))
            inputQueue.add(newDir);

        if (!inputQueue.isEmpty() && isOppositeDir(inputQueue.peek(), currentDirection))
            inputQueue.removeFirst(); // drops the next direction in queue if it is opposite to the current direction
    }

    private boolean isOppositeDir(Direction dir1, Direction dir2) {
        return (dir1 == Direction.DOWN && dir2 == Direction.UP) ||
               (dir1 == Direction.UP && dir2 == Direction.DOWN) ||
               (dir1 == Direction.LEFT && dir2 == Direction.RIGHT) ||
               (dir1 == Direction.RIGHT && dir2 == Direction.LEFT);
    }

    public void draw(Graphics2D frame) {
        frame.setColor(new Color(0x2b331a));
        for (CellPosition pos : body) {
            Point p = pos.getCoordinates();
            frame.fillRect(p.x, p.y, GamePanel.CELL_SIZE, GamePanel.CELL_SIZE);
        }
    }

    // checks if snake collides with any object at given position
    public boolean checkCollisionWith(CellPosition pos) {
        return body.contains(pos);
    }

    public boolean checkCollisionWith(ArrayList<CellPosition> pos) {
        if (pos.isEmpty()) return false;

        for (CellPosition p : pos) {
            if (body.contains(p)) return true;
        }
        return false;
    }
}
