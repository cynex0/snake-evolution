import java.awt.*;

public class BonusFood extends Food {
    private String icon;

    public BonusFood() {
        super();
    }

    @Override
    public void respawn() {
        super.respawn();
        setType();
    }

    @Override
    public void draw(Graphics2D frame) {
        super.draw(frame);
        Point coords = foodLocation.getCoordinates();

        Font font = new Font("Public Pixel", Font.BOLD, 11);
        frame.setFont(font);

        FontMetrics metrics = frame.getFontMetrics(font); // for position calculation
        float x = coords.x + GamePanel.CELL_SIZE / 2f - metrics.stringWidth(icon) / 2f;
        float y = coords.y + GamePanel.CELL_SIZE / 2f - metrics.getHeight() / 2f + metrics.getAscent();

        frame.setColor(Color.BLACK);
        frame.drawString(icon, x, y);
    }

    private void setType() {
        FoodType newFoodType = FoodType.SPEEDFOOD;
        int randInt = rand.nextInt(4) +1;
        switch (randInt) {
            case 1 -> {
                newFoodType = FoodType.SPEEDFOOD;
                this.color = Color.blue;
                this.icon = "^";
            }
            case 2 -> {
                newFoodType = FoodType.SLOWFOOD;
                this.color = Color.yellow;
                this.icon = "v";
            }
            case 3 -> {
                newFoodType = FoodType.PLUSFOOD;
                this.color = Color.green;
                this.icon = "2";
            }
            case 4 -> {
                newFoodType = FoodType.MINUSFOOD;
                this.color = Color.red;
                this.icon = "2";
            }
        }
        this.type = newFoodType;
    }
}
