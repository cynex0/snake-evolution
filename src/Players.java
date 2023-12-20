public class Players implements Comparable<Players> {

    private String name;
    private long score;

    public Players(String name, long score){
        this.name = name;
        this.score = score;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setScore(long score) {
        this.score = score;
    }
    public long getScore(){
        return this.score;
    }

    public String getNamesAndScores(){
        String truncatedName = this.name;
        if (this.name.length() > 3) {
            truncatedName = this.name.substring(0, 3);
        }
        return String.format("%-4S--------------%2s", truncatedName, this.score);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Players)) return false;

        Players other = (Players) o;
        return this.name.equals(other.getName()) &&
                this.score == other.getScore();
    }

    @Override
    public int compareTo(Players other) {
        if (this.score < other.score) return 1;
        if (this.score > other.score) return -1;
        return this.name.compareTo(other.getName());
    }
}
