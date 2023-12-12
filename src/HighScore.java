import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

    public class HighScore extends JPanel implements ActionListener{
        public static final int BORDER_SIZE = 5;
        public static final int MARGIN_DIST = 50; // distance from screen edge to inner margin point
        public static final int MARGIN_W = MARGIN_DIST - BORDER_SIZE;

        public BgPanel panel; // BgPanel reference for instantiation

        private GameState GameState;
        private final Button retry;
        private final Button exit;

        private final ArrayList<Button> buttons;

        private StateChangeListener stateChanger;

        public HighScore(StateChangeListener listener) {
            panel = new BgPanel();
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

        public void paintComponent(Graphics g) { //calling the BgPanel paintComponent method to draw the border rectangles
            super.paintComponent(g);
            panel.paintComponent(g);
        }

        public void actionPerformed(ActionEvent event) {
            String actionCommand = event.getActionCommand();

            if ("retry".equals(actionCommand)) {
                stateChanger.changeState(GameState.GAME);
            } else if ("Main Menu".equals(actionCommand)) {
                stateChanger.changeState(GameState.MENU);
            }
        }
    }


