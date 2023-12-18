public class Players implements Comparable<Players> {

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
        this.score++;
        return score;
    }


    public String getNamesAndScores(){
        String truncatedName = this.name;
        if (this.name.length() > 5) {
            truncatedName = this.name.substring(0, 5);
        }
        return String.format("%-5S-------------%3s", truncatedName, this.score);
    }

    @Override
    public int compareTo(Players other) {
        if (this.score < other.score) return 1;
        if (this.score > other.score) return -1;
        return 0;
    }
}
