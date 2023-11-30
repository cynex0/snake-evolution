import java.awt.*;
import java.io.*;
import javax.swing.*;



public class Main {
    public static final Point WINDOW_SIZE = new Point(800, 800);

    public static void main(String[] args) throws IOException {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Snake Evolution");
        window.setSize(WINDOW_SIZE.x, WINDOW_SIZE.y);

        GamePanel gamePanel = new GamePanel();
        GameOver gameOver = new GameOver();
        //LeaderBoard lb = new LeaderBoard();
        window.add(gamePanel);
        //window.add(lb);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGame();



    }
}
