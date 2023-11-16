import javax.swing.JPanel;
import java.awt.*;

public class BgPanel extends JPanel {
    public static final int BORDER_SIZE = 5;
    public static final int MARGIN_DIST = 50; // distance from screen edge to inner margin point
    public static final int MARGIN_W = MARGIN_DIST - BORDER_SIZE; // margin width excluding border thickness

    public BgPanel() {
        super();
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D frame = (Graphics2D) g;

        // fill inner background
        frame.setColor(new Color(0xA9E000));
        frame.fillRect(MARGIN_DIST, MARGIN_DIST, Main.WINDOW_SIZE.x - 2*MARGIN_DIST, Main.WINDOW_SIZE.y - 2*MARGIN_DIST);

        // draw borders
        frame.setColor(Color.WHITE);

        frame.fillRect(MARGIN_W, MARGIN_W, BORDER_SIZE, Main.WINDOW_SIZE.y - 2 * MARGIN_W); // left border
        frame.fillRect(Main.WINDOW_SIZE.x - MARGIN_DIST, MARGIN_W, BORDER_SIZE, Main.WINDOW_SIZE.y - 2 * MARGIN_W); // left border
        frame.fillRect(MARGIN_W, MARGIN_W, Main.WINDOW_SIZE.x - 2 * MARGIN_W, BORDER_SIZE); // top border
        frame.fillRect(MARGIN_W, Main.WINDOW_SIZE.y - MARGIN_DIST, Main.WINDOW_SIZE.x - 2 * MARGIN_W, BORDER_SIZE); // bottom border

        frame.dispose();
    }
}
