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

        frame.setFont(new Font("Public Pixel", Font.BOLD, 12));
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
