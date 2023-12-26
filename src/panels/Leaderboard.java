package panels;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

import utilities.Player;
import utilities.GameButton;
import main.engine.*;


public class Leaderboard extends JPanel implements ActionListener {
    private DefaultListModel<String> listItems;
    private JList<String> lbList;
    private GameButton mainMenuBtn;     // a Button to go back to the main menu
    private BgPanel bg;

    private static ArrayList<Player> playerList = new ArrayList<>();

    private StateChangeListener stateChanger;

    public Leaderboard(StateChangeListener listener){
        bg = new BgPanel();
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

        mainMenuBtn = new GameButton("Main Menu");
        mainMenuBtn.setFocusable(true);
        mainMenuBtn.setActionCommand("MENU");

        this.add(Box.createRigidArea(new Dimension(0, 450))); // adds empty area before MM button
        this.add(mainMenuBtn);
        mainMenuBtn.addActionListener(this);
        stateChanger = listener;

        readToList();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        bg.paintComponent(g);// Drawing black border on the frame
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();

        if ("MENU".equals(actionCommand)) {
            stateChanger.changeState(GameState.MENU); // switches to main menu.
        }
    }

    // Returns a sorted list of all players read from the json file
    private static ArrayList<Player> readFromFile() {
        JSONParser parser = new JSONParser();
        ArrayList<Player> players = new ArrayList<>();
        File file = new File("res/Top10Scores.json");

        try {
            if (!file.exists()) {
                JSONObject jsonObj = new JSONObject();
                FileWriter writer = new FileWriter(file);
                writer.write(jsonObj.toJSONString());
                writer.close();
            } else {
                FileReader reader = new FileReader(file); // Creating reader to read data from json file
                JSONObject readJsonObj = (JSONObject) parser.parse(reader); // parsing data from json file to string

                for (Object PlayersData : readJsonObj.keySet()) {
                    String playerName = (String) PlayersData;
                    long playerScore = (long) readJsonObj.get(PlayersData);
                    Player player = new Player(playerName, playerScore);
                    players.add(player);
                }

                // sorting players from high to low scores
                Collections.sort(players);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Error while reading file!\n" + e.getMessage());
        }

        return players;
    }

    // adds 10 top scoring players to the list for display
    private void readToList() {
        ArrayList<Player> top10Scorers = readFromFile();

        // filling the list with top 10 players names and scores
        int playerIndex = 1;
        for (Player player : top10Scorers) {
            if (playerIndex < 10)
                listItems.addElement(playerIndex + " . " + player.getNamesAndScores());
            else if (playerIndex == 10)
                listItems.addElement(playerIndex + ". " + player.getNamesAndScores());
            else
                break; // stop iterating after 10th player
            playerIndex++;
        }
    }

    public static void createPlayer(String name, long score){
        ArrayList<Player> currentPlayers = readFromFile();

        FileWriter writer;
        JSONObject jsonObj = new JSONObject();

        playerList.addAll(currentPlayers);
        Player newPlayer = new Player(name, score);
        playerList.add(newPlayer);

        // Storing top 10 players' names and scores in json file
        try {
            writer = new FileWriter("res/Top10Scores.json");
            for (Player player : playerList) {
                if (jsonObj.containsKey(player.getName()) && (long) jsonObj.get(player.getName()) > player.getScore()) {
                    continue; // do not put a lower score if an entry with the same name exists
                }
                jsonObj.put(player.getName(), player.getScore());
            }
            writer.write(jsonObj.toJSONString());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // check if players score is among top 10.
    public static boolean isTopTen(Player playerInTop10){
        ArrayList<Player> players = readFromFile();
        if (players.size() < 10) return true;
        return playerInTop10.getScore() > players.get(9).getScore();
    }
}
