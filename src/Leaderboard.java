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
    private static DefaultListModel<String> listItems;
    private static JList<String> lbList;
    private JButton mainMenu;     // a Button to go back to the main menu
    private StateChangeListener stateChanger;

    private BgPanel panel;
    private static ArrayList<Players> playersList = new ArrayList<Players>();

    public Leaderboard(StateChangeListener listener){
        panel = new BgPanel();
        // creating custom font for the game
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //creates a box layout for the panel.
        this.setPreferredSize(new Dimension(GameFrame.WINDOW_SIZE.x, GameFrame.WINDOW_SIZE.y));
        this.setBackground(Color.decode("#A9E000")); // sets the color to the nokia snake green background color.

        JLabel titleLabel = new JLabel("Leaderboard", SwingConstants.CENTER); //creates the title "snake evolution" for the menu.
        titleLabel.setForeground(Color.BLACK); //colors it black.
        titleLabel.setFont(new Font("Public Pixel", Font.BOLD,25));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); //centers the text.

        // defining layout of list
        listItems = new DefaultListModel<>(); // A list that will hold players names and score
        lbList = new JList<>(listItems); // a list that will define the layout ie color, size, etc
        lbList.setBackground(Color.decode("#A9E000"));
        lbList.setForeground(Color.BLACK);
        lbList.setFont(new Font("Public Pixel", Font.BOLD,25));

        this.add(Box.createRigidArea(new Dimension(0, 10)));//creates a blank area above title for visual spacing.
        this.add(titleLabel); // adds title to panel.
        this.add(Box.createRigidArea(new Dimension(0, 50)));// drawing blank area above JList
        this.add(lbList);

        mainMenu = new Button("MAIN MENU");
        mainMenu.setFocusable(true);
        mainMenu.setActionCommand("MENU");

        this.add(Box.createRigidArea(new Dimension(0, 250))); // adds empty area before MM button
        this.add(mainMenu);
        mainMenu.addActionListener(this);
        stateChanger = listener;

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

    private static ArrayList<Players> readFromFile() {
        // Returns a sorted list of players read from the json file
        JSONParser parser = new JSONParser();
        ArrayList<Players> players = new ArrayList<>();

        try {
            FileReader reader = new FileReader("res/Top10Scores.json"); // Creating reader to read data from json file
            JSONObject readJsonObj = (JSONObject) parser.parse(reader); // parsing data from json file to string

            for (Object PlayersData : readJsonObj.keySet()) {
                String playerName = (String) PlayersData;
                long playerScore = (long) readJsonObj.get(PlayersData);
                Players player = new Players(playerName, playerScore);
                players.add(player);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Error while reading file!\n" + e.getMessage());
        }

        // sorting players from high to low scores
        Collections.sort(players);
        return players;
    }

    public static void readTop10Players(){
        ArrayList<Players> top10Scorers = readFromFile();

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
    }

    public static void createPlayer(String name, long score){
        ArrayList<Players> top10Scorers = readFromFile();

        FileWriter writer;
        org.json.simple.JSONObject jsonObj = new org.json.simple.JSONObject();

        playersList.addAll(top10Scorers);
        Players newPlayer = new Players(name, score);
        playersList.add(newPlayer);

        // Storing top 10 players' names and scores in json file
        try {
            writer = new FileWriter("res/Top10Scores.json");
            for (Players players : playersList) {
                if (jsonObj.containsKey(players.getName()) && (long) jsonObj.get(players.getName()) > players.getScore()) {
                    continue; // do not put a lower score if an entry with the same name exists
                }
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
        ArrayList<Players> players = readFromFile();
        if (players.size() < 10) return true;
        return playerInTop10.getScore() > players.get(9).getScore();
    }

}
