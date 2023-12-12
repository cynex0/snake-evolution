import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.util.ArrayList;

public class GameOver extends JPanel implements ActionListener {

    public static final int BORDER_SIZE = 5;
    public static final int MARGIN_DIST = 50; // distance from screen edge to inner margin point
    public static final int MARGIN_W = MARGIN_DIST - BORDER_SIZE;


    private GameState GameState;
    private final Button retry;
    private final Button mainmenu;

    private final ArrayList<Button> buttons;

    private StateChangeListener stateChanger;

    public BgPanel panel; // BgPanel reference for instantiation
    private JLabel scoreText;

    public GameOver(StateChangeListener listener) {
        panel = new BgPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000"));

        JLabel titleLabel = new JLabel("GAME OVER!", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD, 25));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(titleLabel);
        this.add(Box.createRigidArea(new Dimension(0, 150)));

        scoreText = new JLabel("YOUR SCORE: " , SwingConstants.CENTER);
        scoreText.setForeground(Color.BLACK);
        scoreText.setFont(new Font("Public Pixel", Font.BOLD, 40));
        scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(scoreText);
        this.add(Box.createRigidArea(new Dimension(0, 220)));

        retry = new Button("Retry");
        mainmenu = new Button("Main Menu");

        buttons = new ArrayList<>();
        buttons.add(retry);
        buttons.add(mainmenu);


        for (Button button : buttons) {
            this.add(button);
            Font newButtonFont = new Font("Public Pixel", Font.BOLD, 35);
            button.setFont(newButtonFont);
            button.setPreferredSize(new Dimension(700, 120));
            button.setMaximumSize(new Dimension(700, 120));
            button.setFocusable(true);
        }

        retry.setActionCommand("retry");
        retry.addActionListener(this);

        mainmenu.setActionCommand("main menu");
        mainmenu.addActionListener( this);

        stateChanger = listener;
    }
    public void paintComponent(Graphics g) { //calling the BgPanel paintComponent method to draw the border rectangles
        super.paintComponent(g);
        panel.paintComponent(g);
    }

    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if ("retry".equals(actionCommand)) {
            stateChanger.changeState(GameState.GAME);
        } else if ("main menu".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU);
        }
    }
}
