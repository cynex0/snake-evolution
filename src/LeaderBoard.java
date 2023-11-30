import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
//import org.json.simple.JSONObject;

public class LeaderBoard extends JPanel {
    public static final int BORDER_SIZE = 5;
    public static final int MARGIN_DIST = 50; // distance from screen edge to inner margin point
    public static final int MARGIN_W = MARGIN_DIST - BORDER_SIZE; // margin width excluding border thickness
    ArrayList<Players> playersList = new ArrayList<Players>();
    //JSONObject jsonObject = new JSONObject();
    public LeaderBoard(){
        super();
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(new Color(0xA9E000));
        this.setDoubleBuffered(true);

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D frame = (Graphics2D) g;
        FileWriter writer;

        // fill inner background
        frame.setColor(new Color(0xA9E000));
        frame.fillRect(MARGIN_DIST, MARGIN_DIST, Main.WINDOW_SIZE.x - 2*MARGIN_DIST, Main.WINDOW_SIZE.y - 2*MARGIN_DIST);

        // draw borders
        frame.setColor(Color.BLACK);

        frame.fillRect(MARGIN_W, MARGIN_W, BORDER_SIZE, Main.WINDOW_SIZE.y - 2 * MARGIN_W); // left border
        frame.fillRect(Main.WINDOW_SIZE.x - MARGIN_DIST, MARGIN_W, BORDER_SIZE, Main.WINDOW_SIZE.y - 2 * MARGIN_W); // left border
        frame.fillRect(MARGIN_W, MARGIN_W, Main.WINDOW_SIZE.x - 2 * MARGIN_W, BORDER_SIZE); // top border
        frame.fillRect(MARGIN_W, Main.WINDOW_SIZE.y - MARGIN_DIST, Main.WINDOW_SIZE.x - 2 * MARGIN_W, BORDER_SIZE); // bottom border

        // Creating dummy players and their scores
        Players player1 = new Players("Wick");
        player1.addScore();
        Players player2 = new Players("Mike");
        player2.addScore();player2.addScore();
        Players player3 = new Players("Pia");
        player3.addScore();player3.addScore();
        Players player4 = new Players("Willy");
        player4.addScore();player4.addScore();player4.addScore();player4.addScore();
        Players player5 = new Players("Teo");
        player5.addScore();player5.addScore();player5.addScore();player5.addScore();player5.addScore();
        player5.addScore();player5.addScore();player5.addScore();player5.addScore();player5.addScore();player5.addScore();
        Players player6 = new Players("Tina");
        player6.addScore();
        Players player7 = new Players("Lia");
        player7.addScore(); player7.addScore(); player7.addScore(); player7.addScore();
        Players player8 = new Players("Lina");
        player8.addScore();player8.addScore();player8.addScore();
        Players player9 = new Players("Lind");
        Players player10 = new Players("Tony");
        player10.addScore();  player10.addScore(); player10.addScore(); player10.addScore();
        player10.addScore();  player10.addScore() ;player10.addScore();

        // Creating players list and adding them in Arraylist
        playersList.add(player1);
        playersList.add(player2);
        playersList.add(player3);
        playersList.add(player4);
        playersList.add(player5);
        playersList.add(player6);
        playersList.add(player7);
        playersList.add(player8);
        playersList.add(player9);
        playersList.add(player10);

        // Sorting players by high to low scores
        for (int i=0; i < playersList.size(); i++){
            for (int j = i+1; j < playersList.size(); j++){
                if (playersList.get(i).getScore() < playersList.get(j).getScore()){
                    Collections.swap(playersList, i , j);
                }
            }
        }
        
        frame.setColor(new Color(0xA9E000));
        frame.fillRect(60,80,300,600);


        int yAxes = 100; // 
        int getIndexOfPlayersList = 0;
        
        // Filling the frame with sorted results (from high to low)
        try {
            writer = new FileWriter("TopScores.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Players players : playersList) {
            frame.setColor(Color.BLACK);
            frame.setFont(new Font("Serif", Font.BOLD, 30));
            frame.drawString("Leader Board", 320, 30);
            // Using the condition to highlight the top scorer
            if (getIndexOfPlayersList == 0){
                frame.setFont(new Font("Times New Roman", Font.BOLD, 22));
                frame.drawString(players.getNamesAndScores(), 300, yAxes);
                try {
                    writer.append(players.getNamesAndScores());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                getIndexOfPlayersList++;
            }
            else {
                frame.setFont(new Font("Times New Roman", Font.ITALIC, 22));
                try {
                    writer.append(players.getNamesAndScores());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                frame.drawString(players.getNamesAndScores(), 300, yAxes);
            }
            yAxes+=20;
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
