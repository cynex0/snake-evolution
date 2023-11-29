import java.awt.*;
import java.io.*;
import javax.swing.*;

public class Main {
    public static final Point WINDOW_SIZE = new Point(800, 800);

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Snake Evolution");
        window.setSize(WINDOW_SIZE.x, WINDOW_SIZE.y);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGame();

        new GameOver();

    }
}
