import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard extends JPanel implements ActionListener {
    Font gameFont = null;
    private static DefaultListModel<String> listItems;
    private static JList<String> lbList;
    private JButton mainMenu;     // a Button to go back to the main menu
    private StateChangeListener stateChanger;

    private BgPanel panel;
    private static ArrayList<Players> playersList = new ArrayList<Players>();

    public Leaderboard(StateChangeListener listener){
        panel = new BgPanel();
        // creating custom font for the game
        try {
            gameFont = Font.createFont(Font.TRUETYPE_FONT,new File("res/PublicPixel.ttf")).deriveFont(25f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(gameFont);

        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000")); // sets the color to the nokia snake green background color.

        JLabel titleLabel = new JLabel("Leaderboard", SwingConstants.CENTER); //creates the title "snake evolution" for the menu.
        titleLabel.setForeground(Color.BLACK); //colors it black.
        titleLabel.setFont(gameFont);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //centers the text.

        // defining layout of list
        listItems = new DefaultListModel<>(); // A list that will hold players names and score
        lbList = new JList<>(listItems); // a list that will define the layout ie color, size, etc
        lbList.setBackground(Color.decode("#A9E000"));
        lbList.setForeground(Color.BLACK);
        lbList.setFont(gameFont);

        this.add(Box.createRigidArea(new Dimension(0, 10)));//creates a blank area above title for visual spacing.
        this.add(titleLabel); // adds title to panel.
        this.add(Box.createRigidArea(new Dimension(0, 50)));// drawing blank area above JList
        this.add(lbList);

        mainMenu = new Button("Main menu");
        mainMenu.setFocusable(true);
        mainMenu.setActionCommand("MENU");

        this.add(mainMenu);
        mainMenu.addActionListener(this);
        stateChanger = listener;

        createPlayer();
        readTop10Players();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        panel.paintComponent(g);// Drawing black border on the frame
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if ("MENU".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU); // switches to main menu.

        }

    }

    public static void readTop10Players(){
        JSONParser parser = new JSONParser();
        try {
            FileReader reader = new  FileReader("res/Top10Scores.json"); // Creating reader to read data from json file
            JSONObject readJsonObj = (JSONObject) parser.parse(reader); // parsing data from json file to string

            // Reading data from json and adding it to an arraylist of Players class
            ArrayList<Players> top10Scorers = new ArrayList<>();
            for (Object PlayersData : readJsonObj.keySet()) {
                String playerName = (String) PlayersData;
                long playerScore = (long) readJsonObj.get(PlayersData);
                Players player = new Players(playerName, playerScore);
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
            // filling the list with top 10 players names and scores
            int playerIndex = 1;
            for (Players player : top10Scorers) {

                if (playerIndex < 10) {

                    listItems.addElement(playerIndex + " . " + player.getNamesAndScores());

                }
                else if (playerIndex == 10) {

                    listItems.addElement(playerIndex + ". " + player.getNamesAndScores());
                }
                playerIndex++;
            }

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }
    public static void createPlayer(){
        FileWriter writer;
        org.json.simple.JSONObject jsonObj = new org.json.simple.JSONObject();

        // Creating dummy players and their scores
        Players player1 = new Players("Ritta",42);

        Players player2 = new Players("Fanny",42);

        Players player3 = new Players("Tiger",2);

        Players player4 = new Players("Tony",90);

        Players player5 = new Players("Lisa",33);

        Players player6 = new Players("Peter", 98);

        Players player7 = new Players("Tomas", 25);

        Players player8 = new Players("Nina", 49);

        Players player9 = new Players("Tim", 66);

        Players player10 = new Players("Eric",12);

        Players player11 = new Players("Larsson", 40);

        Players player12 = new Players("Cecilia", 1000);
        Players player13 = new Players("Linda", 66);
        Players player14 = new Players("Willy", 0);


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
        playersList.add(player13);
        playersList.add(player14);
        isTopTen(player11);

        // Storing top 10 players' names and scores in json file
        try {
              writer = new FileWriter("res/Top10Scores.json");
            for (Players players : playersList) {
                jsonObj.put(players.getName(), players.getScore());
            }
            writer.write(jsonObj.toJSONString());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // check if players score is among top 10.
    public static boolean isTopTen(Players playerInTop10){
        boolean isInTopTen = false;

        JSONParser parser = new JSONParser();
        try{
            FileReader reader = new FileReader("res/Top10Scores.json");//Creating reader to read data from json file
            JSONObject readJsonObj=(JSONObject)parser.parse(reader);//parsing data from json file to  string

            ArrayList<Players> top10Scorers = new ArrayList<>();
            for(Object PlayersData:readJsonObj.keySet()){
                String playerName=(String)PlayersData;
                long playerScore=(long)readJsonObj.get(PlayersData);
                Players player=new Players(playerName, playerScore);
                player.setScore((int)playerScore);
                top10Scorers.add(player);
            }
            for(int i=0;i<top10Scorers.size();i++){
                for(int j=i+1;j<top10Scorers.size();j++){
                    if(top10Scorers.get(i).getScore()<top10Scorers.get(j).getScore()){
                        Collections.swap(top10Scorers,i,j);
                    }
                }

            }
            int playerIndex = 1;
            for (Players player : top10Scorers) {
                if (playerIndex <= 10 && (playerInTop10.getScore() == top10Scorers.get(playerIndex).getScore())) {
                    /*if(playerInTop10.getScore() < top10Scorers.get(playerIndex).getScore()){
                        isInTopTen = false;
                    }*/

                   //if (){
                        isInTopTen = true;
                   //}
                }
                playerIndex++;
            }

        } catch(IOException | ParseException e){
            throw new RuntimeException(e);
        }

        System.out.println(isInTopTen);
        return isInTopTen;
    }

}
