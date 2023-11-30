public class Players {

    private String name;
    private int score;

    public Players(String name){
        this.name = name;
        this.score = 0;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore(){
        return this.score;
    }
    public int addScore(){
        this.score += 10;
        return score;
    }

    public String getNamesAndScores(){
        char ch = '-';
       // String message = String.format("%-20s", this.name) + String.format("%20s", this.score) + "\n";
        return String.format("%1$" + "S", "") + this.name + String.format("%1$" + 20 + "s", " ").replace(' ', ch) + this.score + "\n";
        //return message;
    }

}
