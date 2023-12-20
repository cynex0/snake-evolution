import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Button extends JButton implements MouseListener {
    private final String standardText;

    public Button(String standardText) {
        super(standardText);
        this.standardText = standardText;
        editButton(this);
        setFocusable(true);
        this.addMouseListener(this);
    }

    public void editButton(Button button) {
        Font buttonFont = new Font("Public Pixel", Font.BOLD, 50);
        this.setFont(buttonFont);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setForeground(Color.BLACK);
        this.setContentAreaFilled(false);
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setPreferredSize(new Dimension(700, 170));
        this.setMaximumSize(new Dimension(700, 170
        ));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    public void onHover(boolean hovering) {
        if (hovering) {
            this.setText("<" + standardText + ">" );
        } else {
            this.setText(standardText);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.onHover(true);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.onHover(false);
    }
}

