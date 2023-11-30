import javax.swing.text.html.parser.Entity;
import java.awt.*;

public class CollisionControl {
    GamePanel gp;

    public CollisionControl (GamePanel gp) {

        this.gp = gp;
    }

    public void checkTile (Snake snake) {

        if (snake.isDead()) {
            // GameOver Screen
            System.exit(0);
        }
    }
}
