package panels;

import main.engine.*;
import utilities.GameButton;
import utilities.GameConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * A panel that represents the game-over screen.
 * @author Marwa Abohahcem
 */
public class GameOver extends JPanel implements ActionListener, KeyListener, FocusListener {
    private final GameButton retryBtn; // Declaring button references
    private final GameButton mainMenuBtn;
    public BgPanel bg; // BgPanel reference for instantiation
    private final ArrayList<GameButton> buttons; //declaring arrayList of Buttons to perform redundant button-tasks.

    private StateChangeListener stateChanger;

    private JLabel scoreText;
    private JTextField enterNameField;
    private int score;

    /**
     * Constructs a game-over panel with the player's score. Changes depending on if the score is a new record.
     * Displays a "Game Over" message, player's achieved score, buttons to retry and go to menu (if not a high score) or
     * prompts the player to enter their name/initials (if is a high score).
     * @param listener reference to the observer class to allow state switching
     * @param score player's score to be displayed
     * @param isHighScore a flag to determine if the score is a new record
     * @author Marwa Abohahcem
     * @author Victoria Rönnlid (co-author)
     */
    public GameOver(StateChangeListener listener, int score, boolean isHighScore) {
        this.score = score;
        stateChanger = listener;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameConstants.WINDOW_SIZE.x, GameConstants.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000"));
        this.add(Box.createRigidArea(new Dimension(0, 10))); // Gives some space over title
        JLabel titleLabel = new JLabel("GAME OVER!", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD, 25));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 5))); // creates a blank area under title for visual spacing.
        this.add(titleLabel);

        bg = new BgPanel();
        retryBtn = new GameButton("Retry"); // Assigning buttons
        mainMenuBtn = new GameButton("Main Menu");

        buttons = new ArrayList<>(); // initializing the Button ArrayList.
        buttons.add(retryBtn); // adding the existing Button objects to the list.
        buttons.add(mainMenuBtn);

        if( isHighScore ){
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

            SwingUtilities.invokeLater(() -> {
                enterNameField.requestFocusInWindow();   // Set the focus on the JTextField
            });


        } else {
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

            this.add(Box.createRigidArea(new Dimension(0, 160)));  // Adds space above the new score label

            for (GameButton button : buttons) {
                Font newButtonFont = new Font("Public Pixel", Font.BOLD, 35);
                button.setFont(newButtonFont);
                button.setPreferredSize(new Dimension(700, 120));
                button.setMaximumSize(new Dimension(700, 120));
                this.add(button); // Adds the buttons to the panel.
            }

            retryBtn.setActionCommand("retry");
            retryBtn.addActionListener( this);

            mainMenuBtn.setActionCommand("menu");
            mainMenuBtn.addActionListener(this);

        }
    }

    /**
     * Helper method to generate the text field.
     * @return JTextField object to be put onto the panel for the player's name prompt.
     * @author Marwa Abohahcem
     */
    private JTextField getjTextField() {
        JTextField enterNameField = new JTextField(SwingConstants.CENTER); // Declaring a private static method that returns a JTextField instance, with the initial text (___), and centers the text within the field.
        enterNameField.add(Box.createRigidArea(new Dimension(0,100))); //
        enterNameField.setBorder(BorderFactory.createEmptyBorder()); // Sets an empty border around the text field.
        enterNameField.setForeground(Color.BLACK); // Sets the text color of the field to black.
        enterNameField.setBackground(Color.decode("#A9E000"));
        enterNameField.setFont(new Font("Public Pixel", Font.BOLD,30));
        enterNameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        enterNameField.setMaximumSize(new Dimension(100, 50));
        enterNameField.addFocusListener(this);  //  Allows the user to start typing without manually removing the initial placeholder text.
        enterNameField.addKeyListener(this);

        return enterNameField;
    }

    /**
     * Draws the contents of the panel and the background.
     * @param graphics graphics component supplied by the GameFrame
     * @author Marwa Abohahcem
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        bg.paintComponent(graphics);
    }

    /**
     * Defines the buttons' behaviour.
     * @param event the event to be processed
     * @author Marwa Abohahcem
     */
    @Override
    public void actionPerformed(ActionEvent event ) {
        String actionCommand = event.getActionCommand();

        if ("retry".equals(actionCommand)) {
            stateChanger.changeState(GameState.GAME);
        } else if ("menu".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU);
        }
    }

    /**
     * Limits text field to 3 characters and disallows typing special characters.
     * @param e the key-press event to be processed
     * @author Marwa Abohahcem
     * @author Maksims Orlovs (co-author)
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (enterNameField.getText().length() >= 3 ||(!Character.isLetter(c) && !Character.isWhitespace(c)) )
            e.consume();
    }

    /**
     * Request the observer to switch to the Leaderboard state when the name is entered and 'Enter' is pressed
     * @param e the key-press event to be processed
     * @author Marwa Abohahcem
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER && !enterNameField.getText().isEmpty()){
            Leaderboard.createPlayer(enterNameField.getText(), score);
            stateChanger.changeState(GameState.LEADERBOARD);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * Remove the placeholder text from the text field when it gains focus.
     * @param e the event to be processed
     * @author Marwa Abohahcem
     */
    @Override
    public void focusGained(FocusEvent e) {
        if( enterNameField.getText().isBlank() ){
            enterNameField.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {}
}
