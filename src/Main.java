import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        ArrayList<LeaderBoard> players = new ArrayList<>();

        LeaderBoard player1 = new LeaderBoard("Willy");
        player1.addScore();player1.addScore();player1.addScore();

        LeaderBoard player2 = new LeaderBoard("Halah");
        player2.addScore();

        LeaderBoard player3 = new LeaderBoard("Fatma");
        player3.addScore();player3.addScore();

        LeaderBoard player4 = new LeaderBoard("Zilly");
        player4.addScore();player4.addScore();player4.addScore();player4.addScore();

        LeaderBoard player5 = new LeaderBoard("Bill");
        player5.addScore();player5.addScore();player5.addScore();player5.addScore();

        LeaderBoard player6 = new LeaderBoard("Gates");

        LeaderBoard player7 = new LeaderBoard("John");
        player7.addScore();player7.addScore();player7.addScore();player7.addScore();
        player7.addScore();player7.addScore();player7.addScore();


        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        players.add(player7);

        JLabel leaderBoard = new JLabel();
        leaderBoard.setText("Leader Board");
        leaderBoard.setBounds(10, 50, 600, 100);
        leaderBoard.setFont(new Font("Arial", Font.BOLD, 30));


        DefaultListModel<String> list = new DefaultListModel<>();
        JList playersList = new JList(list);
        playersList.setBounds(10,150,300,300);
        playersList.setBackground(new Color(169,224, 0));

        for (int i = 0; i < players.size()-1; i++){
            for ( int j = i+1; j < players.size(); j++){
                if(players.get(i).getScore() < players.get(j).getScore()){
                    Collections.swap(players,j, i);
                }
            }
        }

        for (LeaderBoard player : players){
            list.addElement(player.getNameAndScore());
        }



        JFrame window = new JFrame();
        window.setSize(800, 800);
        window.setTitle("LEADER BOARD");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setLocationRelativeTo(null); // shows window in the center
        window.setVisible(true);
        window.setResizable(false);
        window.getContentPane().setBackground(new Color(169,224,0));
        window.add(leaderBoard);
        window.add(playersList);

    }
}
