import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoverListener extends MouseAdapter {
    private final Button button;
    public HoverListener(Button btn) {
        super();
        this.button = btn;
    }
    @Override
    public void mouseEntered(MouseEvent e) { // actions when hovering over button
        button.onHover(true);

    }
    @Override
    public void mouseExited(MouseEvent e) { //actions for when not hovering over button
        button.onHover(false);
    }
}