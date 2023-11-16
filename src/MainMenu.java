import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1400, 1200);
        this.setResizable(false);

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




    }
}








