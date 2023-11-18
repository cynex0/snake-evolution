import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.Border;

public class Main {
    public static void main(String[] args) {

     JFrame window = new JFrame();
     window.setSize(800,800);
     window.setVisible(true);
     window.setTitle("LeaderBoard");
     window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     window.setLocationRelativeTo(null); // shows window in the center
     window.getContentPane().setBackground(Color.white);
     window.setResizable(false);


     JButton mainMenu = new JButton();
     mainMenu.setBounds(250,650,300,50);
     mainMenu.setText("Main Menu");
     mainMenu.setFocusable(false);
     mainMenu.setFont(new Font ("TIMES NEW ROMAN",Font.BOLD,25));
     mainMenu.setBorder(BorderFactory.createEtchedBorder());
     window.add(mainMenu);

     JPanel panel1 = new JPanel();
     JPanel panel2 = new JPanel();
     JPanel panel3 = new JPanel();
     JPanel panel4 = new JPanel();

     panel1.setBackground(new Color(0xA9E000));
     panel2.setBackground(new Color(0xA9E000));
     panel3.setBackground(new Color(0xA9E000));
     panel4.setBackground(new Color(0xA9E000));

     panel1.setPreferredSize(new Dimension(40, 40));
     panel2.setPreferredSize(new Dimension(40, 40));
     panel3.setPreferredSize(new Dimension(40, 40));
     panel4.setPreferredSize(new Dimension(40, 40));

     window.add(panel1, BorderLayout.SOUTH);
     window.add(panel2, BorderLayout.EAST);
     window.add(panel3, BorderLayout.WEST);
     window.add(panel4, BorderLayout.NORTH);

     JLabel label = new JLabel();
     label.setSize(80,50);
     label.setText("LeaderBoard");
     label.setBackground(Color.WHITE);
     label.setOpaque(true);
     label.setHorizontalAlignment(JLabel.CENTER);
     label.setVerticalAlignment(JLabel.TOP); // Text within label
     label.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN,40));
     label.setForeground(new Color(0xA9E000));
     window.add(label);


     ArrayList<LeaderBoard> players = new ArrayList<>();

     LeaderBoard player1 = new LeaderBoard("Willy");
     player1.addScore();
     player1.addScore();
     player1.addScore();

     LeaderBoard player2 = new LeaderBoard("Halah");
     player2.addScore();

     LeaderBoard player3 = new LeaderBoard("Fatma");
     player3.addScore();
     player3.addScore();

     LeaderBoard player4 = new LeaderBoard("Zilly");
     player4.addScore();
     player4.addScore();
     player4.addScore();
     player4.addScore();

     LeaderBoard player5 = new LeaderBoard("Bill");
     player5.addScore();
     player5.addScore();
     player5.addScore();
     player5.addScore();

     LeaderBoard player6 = new LeaderBoard("Gates");

     LeaderBoard player7 = new LeaderBoard("John");
     player7.addScore();
     player7.addScore();
     player7.addScore();
     player7.addScore();
     player7.addScore();
     player7.addScore();
     player7.addScore();


     players.add(player1);
     players.add(player2);
     players.add(player3);
     players.add(player4);
     players.add(player5);
     players.add(player6);
     players.add(player7);

     DefaultListModel<String> list = new DefaultListModel<>();
     JList playersList = new JList(list);
     playersList.setBounds(100, 60, 300, 500);
     playersList.setBackground(Color.lightGray);

     for ( int i = 0; i < players.size() - 1; i++ ) {
      for ( int j = i + 1; j < players.size(); j++ ) {
       if ( players.get(i).getScore() < players.get(j).getScore() ) {
        Collections.swap(players, j, i);
       }
      }
     }

     for ( LeaderBoard player : players ) {
      list.addElement(player.getNameAndScore());
     }
     label.add(playersList);

    }
}
