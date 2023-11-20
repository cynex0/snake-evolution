import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class MainMenu extends JFrame {
    private ImageIcon romb;
    private JButton[] buttons;


    public MainMenu() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 1200);
        this.setResizable(false);

        this.romb = new ImageIcon ("C:\\Users\\avr\\Desktop\\ROMB.png");

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.decode("#A9E000"));

        JLabel titleLabel = new JLabel("Snake Evolution", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 100));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(titleLabel);

        JButton start = new JButton("Start");
        editButton(start);
        menuPanel.add(start);

        JButton leaderboard = new JButton("Leaderboard");
        editButton(leaderboard);
        menuPanel.add(leaderboard);

        JButton exit = new JButton("Exit");
        editButton(exit);

        menuPanel.add(exit);

        this.add(menuPanel);

        setVisible(true);

        this.buttons = new JButton[] {start, leaderboard, exit};
        for (JButton button : buttons){
            button.setFocusable(true);
            addKeyBindings(button);
            editButton(button);

        }

        



    }
    public void editButton(JButton button){
        Font buttonFont = new Font("Courier New", Font.BOLD, 80);
        button.setFont(buttonFont);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.BLACK);
        button.setContentAreaFilled(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(700, 300));
        button.setMaximumSize(new Dimension(700, 300));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusable(true);

    }


    public void menuNavigation(JButton[] button) {
        romb = new ImageIcon("C:\\Users\\avr\\Desktop\\ROMB.png\"");





    }



    }











