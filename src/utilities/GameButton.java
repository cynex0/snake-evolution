package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A JButton that conforms to the specified design.
 * @author Victoria Rönnlid
 */
public class GameButton extends JButton implements MouseListener {  // extends the JButton class and uses the mouseListener interface.
    private final String standardText; // declaring variable to store the name of the button as standard text.

    /**
     * Creates and sets up a button object and applies the specified style to it.
     * @param standardText button text to display
     * @author Victoria Rönnlid
     */
    public GameButton(String standardText) { // constructor that takes the button name as parameter.
        super(standardText);
        this.standardText = standardText;
        editButton(this); // calls the editButton function when creating a button.
        setFocusable(true); // calling setFocusable for every button just in case.
        this.addMouseListener(this); // adding a mouseListener to the button upon creation.
    }

    /**
     * Applies the design to the given button.
     * @param button button to apply the design to
     * @author Victoria Rönnlid
     */
    public void editButton(GameButton button) { // function to apply desired button design
        Font buttonFont = new Font("Public Pixel", Font.BOLD, 50); // instantiating a Font object to use as standard font.
        this.setFont(buttonFont); // calling setFont on button, using my buttonFont as parameter.
        this.setBorderPainted(false); // making borders of button disappear and just display the text.
        this.setForeground(Color.BLACK); //setting button text to black.
        this.setContentAreaFilled(false); // making the button transparent.
        this.setBorder(BorderFactory.createEmptyBorder()); // removes the default JButton borders.
        this.setPreferredSize(new Dimension(700, 170)); // setting the size of the buttons to fit the text.
        this.setMaximumSize(new Dimension(700, 170)); // sets maximum size to same as preferred size for consistency.
        this.setAlignmentX(Component.CENTER_ALIGNMENT); // centers the buttons in the window.
    }

    /**
     * Adds and removes a selection effect to the button by adding special characters to the button text.
     * @param hovering a flag that determines if the effect should be applied or removed
     *                 (true - mouse is over the button, false otherwise)
     * @author Victoria Rönnlid
     */
    public void onHover(boolean hovering) { // function for mouse hovering.
        if (hovering) {
            this.setText("<" + standardText + ">" ); // adds the "< >" to the button text upon hovering on it.
        } else {
            this.setText(standardText); // restores the text to only display button text when not hovering.
        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {} //mandatory part of mouseListener interface, but not used.

    @Override
    public void mousePressed(MouseEvent e) {} //mandatory part of mouseListener interface, but not used.

    @Override
    public void mouseReleased(MouseEvent e) {} //mandatory part of mouseListener interface, but not used.

    /**
     * Applies the hovering effect when mouse enters the button area
     * @param e the event to be processed
     * @author Victoria Rönnlid
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.onHover(true);
    }

    /**
     * Removes the hovering effect when mouse exits the button area
     * @param e the event to be processed
     * @author Victoria Rönnlid
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.onHover(false);
    }
}

