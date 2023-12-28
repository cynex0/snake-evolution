package objects.food;

import java.awt.*;
import utilities.GameConstants;

/**
 * Represents bonus food.
 * Extends food.
 * Includes random type selection and appropriate icon and color selection.
 * @author Maksims Orlovs
 * @author Fatemeh Akbarifar
 */
public class BonusFood extends Food {
    private String icon;

    /**
     * Creates a BonusFood instance. Same as food, but uses overridden respawn() that includes random type generation.
     * @see Food#Food()
     * @author Maksims Orlovs
     */
    public BonusFood() {
        super();
    }

    /**
     * Overrides default food respawning. Same as Food, but includes random type generation.
     * @see Food#respawn()
     * @author Maksims Orlovs
     */
    @Override
    public void respawn() {
        super.respawn();
        randType();
    }

    /**
     * Method to draw the food item onto the screen (frame).
     * Same as Food, but includes an appropriate icon based on the type.
     * @param frame Swing Graphics2D object that represents the current frame to be updated.
     * @see Food#draw(Graphics2D)
     * @author Maksims Orlovs
     */
    @Override
    public void draw(Graphics2D frame) {
        super.draw(frame);
        Point coords = foodLocation.getCoordinates();

        Font font = new Font("Public Pixel", Font.BOLD, 11);
        frame.setFont(font);

        FontMetrics metrics = frame.getFontMetrics(font); // for position calculation
        float x = coords.x + GameConstants.CELL_SIZE / 2f - metrics.stringWidth(icon) / 2f;
        float y = coords.y + GameConstants.CELL_SIZE / 2f - metrics.getHeight() / 2f + metrics.getAscent();

        frame.setColor(Color.BLACK);
        frame.drawString(icon, x, y);
    }

    /**
     * Helper method to generate and assign a random type. Assigns the color and icon according to the type.
     * @see FoodType
     * @author Fatemeh Akbarifar
     * @author Maksims Orlovs
     */
    private void randType() {
        switch (rand.nextInt(5)) {
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
            case 4 -> {
                this.type = FoodType.CONTROLINVERTER;
                this.color = new Color(0x68009c);
                this.icon = "?";
            }
        }
    }
}
