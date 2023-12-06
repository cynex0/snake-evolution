import org.json.JSONObject;
//import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class LeaderBoard extends JPanel {
    public static final int BORDER_SIZE = 5;
    public static final int MARGIN_DIST = 50; // distance from screen edge to inner margin point
    public static final int MARGIN_W = MARGIN_DIST - BORDER_SIZE; // margin width excluding border thickness
    ArrayList<Players> playersList = new ArrayList<Players>();

    Font myFont = null;

    //JSONObject jsonObject = new JSONObject();
    public LeaderBoard(){
        super();
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(new Color(0xA9E000));
        this.setDoubleBuffered(true);

        // Setting the custom font for leaderboard
        try {
            myFont = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\halah\\Downloads\\Public_Pixel_Font_1_1\\PublicPixel.ttf")).deriveFont(32f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(myFont);
        } catch (IOException | FontFormatException e) {

        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D frame = (Graphics2D) g;
        FileWriter writer;
        //JSONObject jsonObj = new JSONObject();
        org.json.simple.JSONObject jsonObj = new org.json.simple.JSONObject();
        org.json.simple.JSONObject jsonObj1 = new org.json.simple.JSONObject();
        // fill inner background
        frame.setColor(new Color(0xA9E000));
        frame.fillRect(MARGIN_DIST, MARGIN_DIST, Main.WINDOW_SIZE.x - 2*MARGIN_DIST, Main.WINDOW_SIZE.y - 2*MARGIN_DIST);
        frame.setFont(myFont);
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


        int yAxes = 100;
        int getIndexOfPlayersList = 0;
        int playerIndex = 1;
        
        // Filling the frame with sorted results (from high to low)
        try {
            writer = new FileWriter("Leaderboard.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        frame.setColor(Color.BLACK);

        frame.drawString("LEADERBOARD", 240, 40);
        frame.drawString(" EXIT", 300, 700);
        frame.setFont(myFont.deriveFont(Font.BOLD,18f));

        for (Players players : playersList) {
            if (playerIndex <= 10){
                // Using the condition to highlight the top scorer
                if (getIndexOfPlayersList == 0){
                    frame.drawString(playerIndex + ". " + players.getNamesAndScores(), 100, yAxes);

                    getIndexOfPlayersList++;
                }
                else {

                    frame.setFont(myFont.deriveFont(Font.PLAIN,18f));
                    playerIndex++;
                    frame.drawString(playerIndex +". " + players.getNamesAndScores(), 100, yAxes);
                }
                jsonObj.put(players.getName(), players.getScore());

                yAxes+=50;
            }

        }
        try {
            writer.write(jsonObj.toJSONString());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
