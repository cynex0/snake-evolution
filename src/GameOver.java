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
    private final Button exit;

    private final ArrayList<Button> buttons;

    private StateChangeListener stateChanger;

    public GameOver(StateChangeListener listener) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000"));

        JLabel titleLabel = new JLabel("Game Over!", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD, 25));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0, 3)));
        this.add(titleLabel);

        retry = new Button("Retry");
        exit = new Button("Exit");

        buttons = new ArrayList<>();
        buttons.add(retry);
        buttons.add(exit);


        for (Button button : buttons) {
            this.add(button);
            button.setFocusable(true);
        }

        retry.setActionCommand("retry");
        retry.addActionListener(this);

        exit.setActionCommand("exit");
        exit.addActionListener( this);

        stateChanger = listener;
    }

    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if ("retry".equals(actionCommand)) {
            stateChanger.changeState(GameState.GAME);
        } else if ("exit".equals(actionCommand)) {
            System.exit(0);
        }
    }
}
