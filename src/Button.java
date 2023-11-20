import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Button extends JButton {
    public Button(String text) {
        super(text);
        editButton(this);
    }
    public void editButton(Button button) {
        Font buttonFont = new Font("Courier New", Font.BOLD, 80);
        button.setFont(buttonFont);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.BLACK);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(700, 300));
        button.setMaximumSize(new Dimension(700, 300));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }






}

