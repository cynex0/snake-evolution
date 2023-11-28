import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class GamePanel extends JPanel {
    public static final int CELL_COUNT = 50;
    public static final int CELL_SIZE = (Main.WINDOW_SIZE.x - 2 * (BgPanel.MARGIN_DIST)) / CELL_COUNT;

    public static final int FPS = 60;
    private final BgPanel bg;
    private Snake snake;
    // private final Timer gameLoop;
    private MainMenu menu;

    private GameState state;

    KeyHandler keyH = new KeyHandler(); //creating an instance of the KeyHandler abstract
    public CollisionControl collisionControl = new CollisionControl(this);


    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(Main.WINDOW_SIZE.x, Main.WINDOW_SIZE.y));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH); //To make the gamePanel recognize the key input
        this.setFocusable(true);

        bg = new BgPanel();
        //  snake = new Snake();
        menu = new MainMenu();
        this.add(menu);


     //   public void paintComponent(Graphics g){
     //       super.paintComponent(g);
     //       bg.paintComponent(g); // always draw background first

        }

    }
