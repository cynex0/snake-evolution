import java.awt.*;

public class BonusFood extends Food {
    private String icon;

    public BonusFood() {
        super();
    }

    @Override
    public void respawn() {
        super.respawn();
        randType();
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

    private void randType() {
        switch (rand.nextInt(4)) {
            case 0 -> {
                this.type = FoodType.SPEEDFOOD;
                this.color = new Color(0xffbf00);
                this.icon = "^";
            }
            case 1 -> {
                this.type = FoodType.SLOWFOOD;
                this.color = new Color(0x2F38B4);
                this.icon = "v";
            }
            case 2 -> {
                this.type = FoodType.PLUSFOOD;
                this.color = new Color(0x04B000);
                this.icon = "2";
            }
            case 3 -> {
                this.type = FoodType.MINUSFOOD;
                this.color = new Color(0xd40000);
                this.icon = "2";
            }
        }
    }
}
