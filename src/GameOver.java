import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameOver extends JPanel implements ActionListener {
    private final Button MainMenu;
    private final Button exit;
    public BgPanel panel;

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

        panel = new BgPanel();

        MainMenu = new Button("Main Menu");
        exit = new Button("Exit");

        buttons = new ArrayList<>();
        buttons.add(MainMenu);
        buttons.add(exit);


        for (Button button : buttons) {
            this.add(button);
            button.setFocusable(true);
        }

        MainMenu.setActionCommand("menu");
        MainMenu.addActionListener(this);

        exit.setActionCommand("exit");
        exit.addActionListener( this);

        stateChanger = listener;
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        panel.paintComponent(graphics);


    }
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if ("menu".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU);
        } else if ("exit".equals(actionCommand)) {
            System.exit(0);
        }
    }
}
