import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.util.ArrayList;

public class GameOver extends JPanel implements KeyListener, ActionListener {

    public static final int BORDER_SIZE = 5;
    public static final int MARGIN_DIST = 50; // distance from screen edge to inner margin point
    public static final int MARGIN_W = MARGIN_DIST - BORDER_SIZE;


    private GameState GameState;
    private final Button retry;
    private final Button exit;

    private final ArrayList<Button> buttons;

    public GameOver() {
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

        retry.setActionCommand("start");
        retry.addActionListener(this);

        exit.setActionCommand("exit");
        exit.addActionListener( this);
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D frame = (Graphics2D) graphics;

        // fill inner background
        frame.setColor(new Color(0xA9E000));
        frame.fillRect(MARGIN_DIST, MARGIN_DIST, GameFrame.WINDOW_SIZE.x - 2*MARGIN_DIST, GameFrame.WINDOW_SIZE.y - 2*MARGIN_DIST);

        // draw borders
        frame.setColor(Color.BLACK);

        frame.fillRect(MARGIN_W, MARGIN_W, BORDER_SIZE, GameFrame.WINDOW_SIZE.y - 2 * MARGIN_W); // left border
        frame.fillRect(GameFrame.WINDOW_SIZE.x - MARGIN_DIST, MARGIN_W, BORDER_SIZE, GameFrame.WINDOW_SIZE.y - 2 * MARGIN_W); // left border
        frame.fillRect(MARGIN_W, MARGIN_W, GameFrame.WINDOW_SIZE.x - 2 * MARGIN_W, BORDER_SIZE); // top border
        frame.fillRect(MARGIN_W, GameFrame.WINDOW_SIZE.y - MARGIN_DIST, GameFrame.WINDOW_SIZE.x - 2 * MARGIN_W, BORDER_SIZE); // bottom border


    }
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();


        if ("retry".equals(actionCommand)) {
            GameState = GameState.GAME;
        } else if ("exit".equals(actionCommand)) {
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
