public class LeaderBoard {
    private String name;
    private int score;

    LeaderBoard(String name){
        this.name = name;
        this.score = 0;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public int getScore()
    {
        return this.score;
    }
    public void addScore(){
        this.score += 10;
    }

    public String getNameAndScore(){
        return this.name + "           " + this.score;
    }


}
