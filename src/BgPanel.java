import javax.swing.JPanel;
import java.awt.*;

public class BgPanel extends JPanel {
    public static final int BORDER_THC = 5; // border thickness
    public static final int MARGIN_INNER = GamePanel.CELL_SIZE * 3; // distance from screen edge to inner margin point, 3 cells away
    public static final int MARGIN_OUTER = MARGIN_INNER - BORDER_THC; // distance from screen edge to outer margin point (closer to the screen)

    public BgPanel() {
        super();
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(new Color(0xA9E000));
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D frame = (Graphics2D) g;

        // fill background
        frame.setColor(new Color(0xA9E000));
        frame.fillRect(0, 0, GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y);

        // draw borders
        frame.setColor(Color.BLACK);

        frame.fillRect(MARGIN_OUTER, MARGIN_OUTER, GameFrame.WINDOW_SIZE.x - 2 * MARGIN_OUTER, BORDER_THC); // top border
        frame.fillRect(MARGIN_OUTER, MARGIN_OUTER, BORDER_THC, GameFrame.WINDOW_SIZE.y - 2 * MARGIN_OUTER); // left border
        frame.fillRect(GameFrame.WINDOW_SIZE.x - MARGIN_OUTER - BORDER_THC, MARGIN_OUTER, BORDER_THC, GameFrame.WINDOW_SIZE.y - 2 * MARGIN_OUTER); // right border
        frame.fillRect(MARGIN_OUTER, GameFrame.WINDOW_SIZE.y - MARGIN_OUTER - BORDER_THC, GameFrame.WINDOW_SIZE.x - 2 * MARGIN_OUTER, BORDER_THC); // bottom border
    }
}
