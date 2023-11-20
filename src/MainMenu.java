import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MainMenu extends JPanel implements KeyListener {

    private Button start;
    private Button leaderboard;
    private Button exit;

    private ArrayList<Button> buttons;

    public MainMenu() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.decode("#A9E000"));

        JLabel titleLabel = new JLabel("Snake Evolution", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 100));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);


        start = new Button("Start");
        leaderboard = new Button("Leaderboard");
        exit = new Button("Exit");


        buttons = new ArrayList<>();
        buttons.add(start);
        buttons.add(leaderboard);
        buttons.add(exit);


        for (Button button : buttons) {
            this.add(button);
        }


        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }
    public void keyTyped(KeyEvent key) {

    }

    public void keyPressed(KeyEvent key) {

    }
    public void keyReleased(KeyEvent key) {

    }
}
