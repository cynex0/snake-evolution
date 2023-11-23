import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainMenu extends JPanel { //the mainMenu class javas JPanel

    private Button start; // Declaring Button references
    private Button leaderboard;
    private Button exit;

    private ArrayList<Button> buttons;//declaring arrayList of Buttons to perform redundant button-tasks.
    private int index = 0;// initializing index variable for button navigation in keylistener.
    private KeyHandler keyHandler = new KeyHandler();// initializing new KeyHandler instance.



    public MainMenu() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //creates a box layout for the panel.
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000")); // sets the color to the nokia snake green background color.


        JLabel titleLabel = new JLabel("Snake Evolution", SwingConstants.CENTER); //creates the title "snake evolution" for the menu.
        titleLabel.setForeground(Color.BLACK); //colors it black.
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD, 20)); //changes font and size.
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //centers the text.
        this.add(Box.createRigidArea(new Dimension(0, 3))); //creates a blank area above title for visual spacing.
        this.add(titleLabel); // adds title to panel.



        start = new Button("Start"); //assigning buttons.
        leaderboard = new Button("Leaderboard");
        exit = new Button("Exit");


        buttons = new ArrayList<>();// initializing the Button ArrayList.
        buttons.add(start);// adding the existing Button objects to the list.
        buttons.add(leaderboard);
        buttons.add(exit);


        for (Button button : buttons) { // for each-loop to add the buttons to the JPanel.
            this.add(button);
        }


        this.addKeyListener(keyHandler); //adds keyHandler to panel.
        this.setFocusable(true);
        this.requestFocusInWindow(); //requests focus in window.
    }

    @Override
    protected void paintComponent(Graphics graphics) { //overriding paintComponent for mainMenu.
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);

        int thickness = 5; //variable with the thickness value.
        int margin = 40; //variable for the margin value.

        for (int i = 0; i < thickness; i++) { // drawing the inner rectangle on the JPanel.
            graphics.drawRect(margin - i, margin - i, getWidth() - 1 - (2 * (margin - i)), getHeight() - 1 - (2 * (margin - i)));

            for (int j = 0; j < thickness; j++) { // drawing the border rectangle on the JPanel.
                graphics.drawRect(j, j, getWidth() - 1 - (2 * j), getHeight() - 1 - (2 * j));

            }
        } if (keyHandler.upPressed && index > 0) {// function for up-arrow-key.
            index--;
            keyHandler.upPressed = false;// resets the state of key interaction.
            repaint(); // repainting to refresh the UI.
        }
        if (keyHandler.downPressed && index < buttons.size() - 1) {// function for down-arrow-key.
            index++;
            keyHandler.downPressed = false; //resetting the key interaction again.
            repaint(); // refreshing UI and repainting again.
        }




    }


}



