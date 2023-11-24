import javax.swing.text.html.parser.Entity;
import java.awt.*;

public class CollisionControl {
    GamePanel gp;

    public CollisionControl (GamePanel gp) {

        this.gp = gp;
    }

    public void checkTile (Snake snake) {
        int headX = snake.getBody().get(0).getCell().x;
        int headY = snake.getBody().get(0).getCell().y;

        if (headX > 500 ) {
            //snake.getBody().get(0).setCellX(0);
            snake.setDirection(Direction.LEFT);

        }
        else if (headX < 5) {

            snake.setDirection(Direction.RIGHT);
        }
        else if (headY < 5) {

            snake.setDirection(Direction.DOWN);
        }
        else if (headY >= 400) {

            snake.setDirection(Direction.UP);
        }
    }
}
