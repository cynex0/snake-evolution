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
        switch (rand.nextInt(4)) {
            case 0 -> {
                this.type = FoodType.SPEEDFOOD;
                this.color = Color.blue;
                this.icon = "^";
            }
            case 1 -> {
                this.type = FoodType.SLOWFOOD;
                this.color = Color.yellow;
                this.icon = "v";
            }
            case 2 -> {
                this.type = FoodType.PLUSFOOD;
                this.color = Color.green;
                this.icon = "2";
            }
            case 3 -> {
                this.type = FoodType.MINUSFOOD;
                this.color = Color.red;
                this.icon = "2";
            }
        }
    }
}
