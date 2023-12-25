import java.awt.*;

public class BonusFood extends Food {
    private String icon;

    public BonusFood() {
        super();
    }

    @Override
    public void respawn() {
        setType();
        super.respawn();
    }

    @Override
    public void draw(Graphics2D frame) {
        super.draw(frame);
        Point coords = foodLocation.getCoordinates();

        Font font = new Font("Public Pixel", Font.BOLD, 14);
        frame.setFont(font);
        FontMetrics metrics = frame.getFontMetrics(font); // for position calculation
        coords.x += (GamePanel.CELL_SIZE - metrics.stringWidth(icon)) / 2; // shift to the half of the cell - char width
        coords.y += metrics.getAscent() + (GamePanel.CELL_SIZE - metrics.getHeight()) / 2; // shift to the half of the cell - char height

        frame.setColor(Color.BLACK);
        frame.drawString(icon, coords.x, coords.y);
    }

    private void setType() {
        FoodType newFoodType = FoodType.SPEEDFOOD;
        int randInt = rand.nextInt(4) +1;
        switch (randInt) {
            case 1 -> {
                newFoodType = FoodType.SPEEDFOOD;
                this.color = Color.blue;
                this.icon = "↑";
            }
            case 2 -> {
                newFoodType = FoodType.SLOWFOOD;
                this.color = Color.yellow;
                this.icon = "↓";
            }
            case 3 -> {
                newFoodType = FoodType.PLUSFOOD;
                this.color = Color.green;
                this.icon = "+";
            }
            case 4 -> {
                newFoodType = FoodType.MINUSFOOD;
                this.color = Color.red;
                this.icon = "-";
            }
        }
        this.type = newFoodType;
    }
}
