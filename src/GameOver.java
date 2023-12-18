import org.jetbrains.annotations.NotNull;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameOver extends JPanel implements ActionListener, KeyListener, FocusListener {
    private final Button retryBtn; // Declaring button references
    private final Button mainMenuBtn;
    public BgPanel panel; // BgPanel reference for instantiation
    private final ArrayList<Button> buttons; //declaring arrayList of Buttons to perform redundant button-tasks.

    private StateChangeListener stateChanger;

    private JLabel scoreText;
    private JTextField enterNameField;

    public GameOver(StateChangeListener listener, int score) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000"));
        this.add(Box.createRigidArea(new Dimension(0, 10))); // Gives some space over title
        JLabel titleLabel = new JLabel("GAME OVER!", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD, 25));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 5))); // creates a blank area under title for visual spacing.
        this.add(titleLabel);

        panel = new BgPanel();
        retryBtn = new Button("Retry"); // Assigning buttons
        mainMenuBtn = new Button("Main Menu");

        buttons = new ArrayList<>(); // initializing the Button ArrayList.
        buttons.add(retryBtn); // adding the existing Button objects to the list.
        buttons.add(mainMenuBtn);


        boolean isHighScore = score >= 5; // Temp value to change state based on the score
        if( isHighScore ){ // If the score qualifies as a high score " temp score = 5 ", The player can enter name.

            JLabel newHighScoreLabel = new JLabel("NEW HIGH SCORE!",SwingConstants.CENTER); // A label to display "NEW HIGH SCORE!".
            newHighScoreLabel.setForeground(Color.BLACK);
            newHighScoreLabel.setFont(new Font("Public Pixel", Font.BOLD, 30));
            newHighScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(Box.createRigidArea(new Dimension(0, 100)));  // Add space above the newHighScoreLabel.
            this.add(newHighScoreLabel); // adds the label to the panel

            scoreText = new JLabel("YOUR SCORE:",SwingConstants.CENTER);
            scoreText.setForeground(Color.BLACK);
            scoreText.setFont(new Font("Public Pixel", Font.BOLD, 35)); // Sets the font and size
            scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(Box.createRigidArea(new Dimension(0, 50))); // Adds space above YOUR SCORE label
            this.add(scoreText);
            this.add(Box.createRigidArea(new Dimension(0, 50))); // Adds visual space between the label and score.

            JLabel ScoreLabel = new JLabel(String.valueOf(score),SwingConstants.CENTER); // Adding the score as a label, following our design protoType.
            ScoreLabel.setForeground(Color.BLACK);
            ScoreLabel.setFont(new Font("Public Pixel", Font.BOLD, 35));
            ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(ScoreLabel);

            JLabel EnterNameLabel = new JLabel("ENTER YOUR NAME:",SwingConstants.CENTER);
            EnterNameLabel.setForeground(Color.BLACK);
            EnterNameLabel.setFont(new Font("Public Pixel", Font.BOLD, 30));
            EnterNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(Box.createRigidArea(new Dimension(0, 170)));
            this.add(EnterNameLabel);

            enterNameField = getjTextField(); // Creates a text field
            setVisible(true);
            this.add(Box.createRigidArea(new Dimension(0,50))); // Add the text field to the panel with spacing adjustments.
            this.add(enterNameField);
            this.add(Box.createRigidArea(new Dimension(0, 50)));


        } else {       // if the score is not considered a high score "temp score < 5", go back to menu or retry the game using buttons.
            this.add(Box.createRigidArea(new Dimension(0, 80)));
            scoreText = new JLabel("YOUR SCORE:",SwingConstants.CENTER);
            scoreText.setForeground(Color.BLACK);
            scoreText.setFont(new Font("Public Pixel", Font.BOLD, 35));
            scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(Box.createRigidArea(new Dimension(0, 50)));
            this.add(scoreText);
            this.add(Box.createRigidArea(new Dimension(0, 50)));

            JLabel ScoreLabel = new JLabel(String.valueOf(score),SwingConstants.CENTER);
            ScoreLabel.setForeground(Color.BLACK);
            ScoreLabel.setFont(new Font("Public Pixel", Font.BOLD, 35));
            ScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.add(ScoreLabel);

            for (Button button : buttons) {
                Font newButtonFont = new Font("Public Pixel", Font.BOLD, 35);
                button.setFont(newButtonFont);
                button.setPreferredSize(new Dimension(700, 120));
                button.setMaximumSize(new Dimension(700, 120));
                this.add(Box.createRigidArea(new Dimension(0, 80)));  // Adds space above the new score label
                this.add(button); // Adds the buttons to the panel.
            }

            retryBtn.setActionCommand("retry");
            retryBtn.addActionListener( this);

            mainMenuBtn.setActionCommand("menu");
            mainMenuBtn.addActionListener(this);

            stateChanger = listener;
        }
    }

    @NotNull // Indicates that the method should not return null.
    private JTextField getjTextField() {
        JTextField EnterNameField = new JTextField("___",SwingConstants.CENTER); // Declaring a private static method that returns a JTextField instance, with the initial text (___), and centers the text within the field.
        EnterNameField.add(Box.createRigidArea(new Dimension(0,100))); //
        EnterNameField.setBorder(BorderFactory.createEmptyBorder()); // Sets an empty border around the text field.
        EnterNameField.setForeground(Color.BLACK); // Sets the text color of the field to black.
        EnterNameField.setBackground(Color.decode("#A9E000"));
        EnterNameField.setFont(new Font("Public Pixel", Font.BOLD,30));
        EnterNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        EnterNameField.setMaximumSize(new Dimension(100, 50));

        EnterNameField.addFocusListener(this);  //  Allows the user to start typing without manually removing the initial placeholder text.
        EnterNameField.addKeyListener(this);

        return EnterNameField;
    }

    @Override
    protected void paintComponent(Graphics graphics) { //calling the BgPanel paintComponent method to draw the border rectangles
        super.paintComponent(graphics);
        panel.paintComponent(graphics);

    }
    public void actionPerformed(ActionEvent event) { // Performs action when buttons are clicked
        String actionCommand = event.getActionCommand();

        if ("retry".equals(actionCommand)) {
            stateChanger.changeState(GameState.GAME); // Retry button changes the game over state to the game state.
        } else if ("menu".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU); // Menu button changes the game over  state to main menu.

        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        if (enterNameField.getText().length() >= 3 ) // limit textfield to 3 characters
            e.consume();
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void focusGained(FocusEvent e) {
        if(enterNameField.getText().equals("___")){
            enterNameField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {}
}
