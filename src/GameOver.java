import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameOver extends JPanel implements ActionListener {
    private final Button retry;
    private final Button mainMenu;
    public BgPanel panel;


    private final ArrayList<Button> buttons;

    private StateChangeListener stateChanger;

    private JLabel scoreText;

    public GameOver(StateChangeListener listener, int score, boolean isHighScore) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000"));

        this.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel titleLabel = new JLabel("GAME OVER!", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD, 25));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 5)));
        this.add(titleLabel);

        this.add(Box.createRigidArea(new Dimension(0, 100)));
        scoreText = new JLabel("YOUR SCORE: " + score , SwingConstants.CENTER);
        scoreText.setForeground(Color.BLACK);
        scoreText.setFont(new Font("Public Pixel", Font.BOLD, 35));
        scoreText.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(scoreText);
        this.add(Box.createRigidArea(new Dimension(0, 150)));

        JLabel EnterNameLabel = new JLabel("Enter your name:");
        EnterNameLabel.setForeground(Color.BLACK);
        EnterNameLabel.setFont(new Font("Public Pixel", Font.BOLD, 25));
        EnterNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(EnterNameLabel);
        this.add(Box.createRigidArea(new Dimension(0, 100)));

        panel = new BgPanel();
        retry = new Button("Retry");
        mainMenu = new Button("Main Menu");

        buttons = new ArrayList<>();
        buttons.add(retry);
        buttons.add(mainMenu);


        for (Button button : buttons) {
            this.add(button);
            Font newButtonFont = new Font("Public Pixel", Font.BOLD, 35);
            button.setFont(newButtonFont);
            button.setPreferredSize(new Dimension(700, 120));
            button.setMaximumSize(new Dimension(700, 120));
            button.setFocusable(true);

            if (!isHighScore){ // Debug
                button.setFocusable(false);

            }
        }

        retry.setActionCommand("retry");
        retry.addActionListener( this);

        mainMenu.setActionCommand("menu");
        mainMenu.addActionListener(this);

        stateChanger = listener;
    }



    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        panel.paintComponent(graphics);


    }
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if ("retry".equals(actionCommand)) {
            stateChanger.changeState(GameState.GAME);
        } else if ("menu".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU);

        }
    }


}
