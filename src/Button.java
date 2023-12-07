import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Button extends JButton {
    private final String standardText;

    public Button(String standardText) {
        super(standardText);
        this.standardText = standardText;
        editButton(this);
    }
    public void editButton(Button button) {
        Font buttonFont = new Font("Public Pixel", Font.BOLD, 50);
        button.setFont(buttonFont);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.BLACK);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(700, 200));
        button.setMaximumSize(new Dimension(700, 200));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }
    public void hoverAction(boolean hovering) {
        if (hovering) {
            this.setText("◆" + standardText );
        } else {
            this.setText(standardText);
        }
    }






}

