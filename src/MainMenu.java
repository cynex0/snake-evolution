import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MainMenu extends JPanel implements ActionListener { //the mainMenu class javas JPanel & implements ActionListener interface.

    private GameState GameState;
    private Button start; // Declaring Button references
    private Button leaderboard;
    private Button exit;

    private ArrayList<Button> buttons;//declaring arrayList of Buttons to perform redundant button-tasks.

    private StateChangeListener stateChanger;

    public MainMenu(StateChangeListener listener) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000")); // sets the color to the nokia snake green background color.

        JLabel titleLabel = new JLabel("Snake Evolution", SwingConstants.CENTER); //creates the title "snake evolution" for the menu.
        titleLabel.setForeground(Color.BLACK); //colors it black.
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD, 25)); //changes font and size.
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
            button.setFocusable(true);
        }

        start.setActionCommand("start");
        start.addActionListener(this);

        leaderboard.setActionCommand("leaderboard");
        leaderboard.addActionListener(this);

        exit.setActionCommand("exit");
        exit.addActionListener(this);

        stateChanger = listener;
    }

    @Override
    protected void paintComponent(Graphics graphics) { //TODO: Implement drawing frames?
        super.paintComponent(graphics);


    }
    public void actionPerformed(ActionEvent event) { // logic for when buttons are clicked.
        String actionCommand = event.getActionCommand();

        if ("start".equals(actionCommand)) {
            stateChanger.changeState(GameState.GAME); // switches to state GAME.
            System.out.println("Switching");
        } else if ("leaderboard".equals(actionCommand)) {
            stateChanger.changeState(GameState.LEADERBOARD); // switches to state LEADERBOARD.
        } else if ("exit".equals(actionCommand)) {
            System.exit(0); // terminates the program.
        }
    }
}







