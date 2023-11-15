import java.awt.*;


import javax.swing.*;
import javax.swing.border.Border;

import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.NORTH;


public class Main {
    public static void main(String[] args) {

        Border border = BorderFactory.createLineBorder(Color.BLACK,7);

        JLabel label = new JLabel();
        label.setSize(0,50);
        label.setText("LEADERBOARD");
        //label.setVerticalTextPosition(JLabel.CENTER);
        label.setForeground(new Color(0xA9E000));
        label.setHorizontalAlignment(JLabel.CENTER);

        label.setBackground(Color.WHITE); //Good
        label.setOpaque(true); // display background colour

      // label.setVisible(true);
       label.setFont(new Font("MV Boli", Font.PLAIN,40));
       label.setBorder(border);
       label.setBounds(0,0,0,0);
       label.setVerticalAlignment(JLabel.TOP); // Text within label




        JFrame window = new JFrame();
        window.setSize(800, 800); // sets the x-dimension, and y-dimension
        window.setLayout(new BorderLayout());
        window.setVisible(true);
        window.setTitle("LeaderBoard"); // Added
        window.getContentPane().setBackground(new Color(0xA9E000));
        window.add(label);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setBackground(new Color(0xA9E000));
        panel2.setBackground(new Color(0xA9E000));
        panel3.setBackground(new Color(0xA9E000));
        panel4.setBackground(new Color(0xA9E000));

        panel1.setPreferredSize(new Dimension(40,40));
        panel2.setPreferredSize(new Dimension(40,40));
        panel3.setPreferredSize(new Dimension(40,40));
        panel4.setPreferredSize(new Dimension(40,40));

        window.add(panel1,BorderLayout.SOUTH);
        window.add(panel2,BorderLayout.EAST);
        window.add(panel3,BorderLayout.WEST);
        window.add(panel4,BorderLayout.NORTH);















    }
}
