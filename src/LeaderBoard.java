
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



public class LeaderBoard extends JPanel implements ActionListener {
    public static final int BORDER_SIZE = 5;
    public static final int MARGIN_DIST = 50; // distance from screen edge to inner margin point
    public static final int MARGIN_W = MARGIN_DIST - BORDER_SIZE; // margin width excluding border thickness
    private static DefaultListModel<String> listItems;
    private static JList<String> lbList;

    private JButton mainMenu;     // a Button to go back to the main menu
    private StateChangeListener stateChanger;
    private static ArrayList<Players> playersList = new ArrayList<Players>();

    public LeaderBoard(StateChangeListener listener){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000")); // sets the color to the nokia snake green background color.

        JLabel titleLabel = new JLabel("Leaderboard", SwingConstants.CENTER); //creates the title "snake evolution" for the menu.
        titleLabel.setForeground(Color.BLACK); //colors it black.
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD, 25)); //changes font and size.
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //centers the text.
        listItems = new DefaultListModel<>(); // A list that will hold players names and score
        lbList = new JList<>(listItems); // a list that will define the layout ie color, size, etc
        this.add(Box.createRigidArea(new Dimension(0, 3))); //creates a blank area above title for visual spacing.
        this.add(titleLabel); // adds title to panel.
        this.add(lbList);
        createPlayers();
        readTop10Players();

        mainMenu = new Button("Main menu");
        mainMenu.setFocusable(true);
        mainMenu.setBounds(400,700,200,400);
        mainMenu.setActionCommand("MENU");

        this.add(mainMenu);
        mainMenu.addActionListener(this);
        stateChanger = listener;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if ("MENU".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU); // switches to state GAME.

        }

    }

    public static void readTop10Players(){
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new  FileReader("Top10Scores.json"); // Creating reader to read data from json file
            JSONObject readJsonObj = (JSONObject) parser.parse(reader); // parsing data from json file to string

            lbList.setBounds(100,100,700,700);
            lbList.setBackground(Color.decode("#A9E000"));
            lbList.setFont(new Font("Public Pixel", Font.BOLD, 25));
            lbList.setAlignmentX(CENTER_ALIGNMENT);

            // Reading data from json and adding it to an arraylist of Players class
            ArrayList<Players> top10Scorers = new ArrayList<>();
            for (Object PlayersData : readJsonObj.keySet()) {
                String playerName = (String) PlayersData;
                long playerScore = (Long) readJsonObj.get(PlayersData);
                Players player = new Players(playerName);
                player.setScore((int) playerScore);
                top10Scorers.add(player);
            }
            // sorting players from high to low scores
            for (int i=0; i < top10Scorers.size(); i++){
                for (int j = i+1; j < top10Scorers.size(); j++){
                    if (top10Scorers.get(i).getScore() < top10Scorers.get(j).getScore()){
                        Collections.swap(top10Scorers, i , j);
                    }
                }

            }
            int playerIndex = 1;
            for (Players player : top10Scorers) {
                if (playerIndex <= 10) {
                    listItems.addElement(playerIndex + ". " + player.getNamesAndScores());
                    playerIndex++;
                }
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static void createPlayers(){
        FileWriter writer;
        org.json.simple.JSONObject jsonObj = new org.json.simple.JSONObject();

        // Creating dummy players and their scores
        Players player1 = new Players("Wick");
        player1.addScore(); // addScore will add 10 points to the players current score
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

        Players player11 = new Players("Lucas");
        player11.addScore();player11.addScore();
        Players player12 = new Players("Sam");
        player12.addScore();player12.addScore();player12.addScore();

        // Adding dummy  players to an Arraylist
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
        playersList.add(player11);
        playersList.add(player12);

        // Storing top 10 players' names and scores in json file
        try {
            writer = new FileWriter("Top10Scores.json");
            for (Players players : playersList) {
                jsonObj.put(players.getName(), players.getScore());
            }
            writer.write(jsonObj.toJSONString());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
