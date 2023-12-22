import java.awt.*;

public class BonusFood extends Food {
    public BonusFood() {
        super();
    }

    @Override
    public void respawn() {
        setType();
        super.respawn();
    }

    private void setType() {
        FoodType newFoodType = FoodType.SPEEDFOOD;
        int randInt = rand.nextInt(4) +1;
        switch (randInt) {
            case 1 -> {
                newFoodType = FoodType.SPEEDFOOD;
                this.color = Color.blue;
            }
            case 2 -> {
                newFoodType = FoodType.SLOWFOOD;
                this.color = Color.yellow;
            }
            case 3 -> {
                newFoodType = FoodType.PLUSFOOD;
                this.color = Color.green;
            }
            case 4 -> {
                newFoodType = FoodType.MINUSFOOD;
                this.color = Color.red;
            }
        }
        this.type = newFoodType;
    }
}
