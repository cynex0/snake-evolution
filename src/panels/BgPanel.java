package panels;

import javax.swing.JPanel;
import java.awt.*;

import static utilities.GameConstants.*;

public class BgPanel extends JPanel {
    public BgPanel() {
        super();
        this.setPreferredSize(new Dimension(WINDOW_SIZE.x, WINDOW_SIZE.y));
        this.setBackground(new Color(0xA9E000));
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D frame = (Graphics2D) g;

        // fill background
        frame.setColor(new Color(0xA9E000));
        frame.fillRect(0, 0, WINDOW_SIZE.x, WINDOW_SIZE.y);

        // draw borders
        frame.setColor(Color.BLACK);

        frame.fillRect(MARGIN_OUTER, MARGIN_OUTER, WINDOW_SIZE.x - 2 * MARGIN_OUTER, BORDER_THC); // top border
        frame.fillRect(MARGIN_OUTER, MARGIN_OUTER, BORDER_THC, WINDOW_SIZE.y - 2 * MARGIN_OUTER); // left border
        frame.fillRect(WINDOW_SIZE.x - MARGIN_OUTER - BORDER_THC, MARGIN_OUTER, BORDER_THC, WINDOW_SIZE.y - 2 * MARGIN_OUTER); // right border
        frame.fillRect(MARGIN_OUTER, WINDOW_SIZE.y - MARGIN_OUTER - BORDER_THC, WINDOW_SIZE.x - 2 * MARGIN_OUTER, BORDER_THC); // bottom border

        // draw outer border frame
        frame.fillRect(0, 0, WINDOW_SIZE.x, BORDER_THC); // top border
        frame.fillRect(0, 0, BORDER_THC, WINDOW_SIZE.y); //left border
        frame.fillRect(WINDOW_SIZE.x - BORDER_THC, 0, BORDER_THC, WINDOW_SIZE.y); // right border
        frame.fillRect(0, WINDOW_SIZE.y - BORDER_THC, WINDOW_SIZE.x, BORDER_THC); // bottom border
    }
}
