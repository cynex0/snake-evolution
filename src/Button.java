import javax.swing.*;
import java.awt.*;

public class Button extends JButton {
    public boolean hovering = false;
    public Button(String text) {
        super(text);
        editButton(this);
    }

    public void drawShape(Graphics g){
        int shapeSize = 20;
        int[] xDimensions = {2, shapeSize, 2, -shapeSize};
        int[] yDimensions = {-shapeSize, 2, shapeSize, 2};
        g.setColor(Color.BLACK);
        g.fillPolygon(xDimensions, yDimensions, 4);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(hovering){
            drawShape(g);
        }
    }

    public void editButton(Button button) {
        Font buttonFont = new Font("Public Pixel", Font.BOLD, 50);
        button.setFont(buttonFont);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.BLACK);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(700, 100));
        button.setMaximumSize(new Dimension(700, 100));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }






}

