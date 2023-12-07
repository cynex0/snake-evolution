import java.awt.*;

public class Food {
    private Point foodLocation;
    private final int foodSize = GamePanel.CELL_SIZE-2 ;

    public Food (int x , int y){
        this.foodLocation = new CellPosition(x, y);
    }

    public void setFoodLocation (int x, int y){
        this.foodLocation.setLocation(x, y);
    }
    public void draw (Graphics g) {
        g.setColor(Color.BLUE);
        g.drawRect(foodLocation.x, foodLocation.y, foodSize, foodSize);
        g.setColor(Color.GRAY);
        g.fillRect(foodLocation.x, foodLocation.y, foodSize, foodSize);
    }

    public Point getFoodLocation () {
        return this.foodLocation;
    }
}
