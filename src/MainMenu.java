import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class MainMenu extends JPanel implements ActionListener { //the mainMenu class javas JPanel & implements ActionListener interface.

    private GameState GameState;
    private Button start; // Declaring Button references
    private Button leaderboard;
    private Button exit;
    public BgPanel panel; // BgPanel reference for instantiation

    private ArrayList<Button> buttons;//declaring arrayList of Buttons to perform redundant button-tasks.

    private StateChangeListener stateChanger;

    public MainMenu(StateChangeListener listener) {
        panel = new BgPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000")); // sets the color to the nokia snake green background color.

        JLabel titleLabel = new JLabel("Snake Evolution", SwingConstants.CENTER); //creates the title "snake evolution" for the menu.
        titleLabel.setForeground(Color.BLACK); //colors it black.
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD, 25)); //changes font and size.
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //centers the text.
        this.add(Box.createRigidArea(new Dimension(0, 15))); //creates a blank area above title for visual spacing.
        this.add(titleLabel); // adds title to panel.
        this.add(Box.createRigidArea(new Dimension(0, 50)));

        start = new Button("Start"); //assigning buttons.
        leaderboard = new Button("Leaderboard");
        exit = new Button("Exit");

        buttons = new ArrayList<>();// initializing the Button ArrayList.
        buttons.add(start);// adding the existing Button objects to the list.
        buttons.add(leaderboard);
        buttons.add(exit);


        for (Button button : buttons) {
            this.add(button); // add the buttons to the panel
            button.setFocusable(true);
            button.addMouseListener(new MouseAdapter() { //adds mouselistener to hover over buttons
                @Override
                public void mouseEntered(MouseEvent e) { // actions when hovering over button
                    button.hoverAction(true);

                }
                @Override
                public void mouseExited(MouseEvent e) { //actions for when not hovering over button
                    button.hoverAction(false);
                }
            });
        }


        start.setActionCommand("start"); //sets action command for the button
        start.addActionListener(this); //adds listener to register button interaction

        leaderboard.setActionCommand("leaderboard");
        leaderboard.addActionListener(this);

        exit.setActionCommand("exit");
        exit.addActionListener(this);

        stateChanger = listener;
    }
    public void paintComponent(Graphics g) { //calling the BgPanel paintcomponent method to draw the border rectangles
        super.paintComponent(g);
        panel.paintComponent(g);
    }

    public void actionPerformed(ActionEvent event) { // logic for when buttons are clicked.
        String actionCommand = event.getActionCommand();

        if ("start".equals(actionCommand)) {
            stateChanger.changeState(GameState.GAME); // switches to state GAME.
        } else if ("leaderboard".equals(actionCommand)) {
            stateChanger.changeState(GameState.LEADERBOARD); // switches to state LEADERBOARD.
        } else if ("exit".equals(actionCommand)) {
            System.exit(0); // terminates the program.
        }
    }
}







