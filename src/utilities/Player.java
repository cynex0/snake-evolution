package utilities;


/**
 * Represents a player that can be added to the leaderboard
 */
public class Player implements Comparable<Player> {

    private String name;
    private long score;

    /**
     * Creates a player object with specified parameters.
     * @param name player's name
     * @param score player's score
     */
    public Player(String name, long score){
        this.name = name;
        this.score = score;
    }

    /**
     * Getter for the Player's name
     * @return player's name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for the Player's score
     * @return player's score
     */
    public long getScore(){
        return this.score;
    }

    /**
     * Creates a String representing a line in the leaderboard. Formatted according to the design (ABC-----1).
     * @return Leaderboard line with the player's name and score
     */
    public String getNamesAndScores(){
        String truncatedName = this.name;
        if (this.name.length() > 3) {
            truncatedName = this.name.substring(0, 3);
        }
        return String.format("%-4S--------------%2s", truncatedName, this.score);
    }

    /**
     * Compares the Player to another object.
     * @param o an object to be compared with this object
     * @return true if names and scores of both players are equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (!(o instanceof Player)) return false;

        Player other = (Player) o;
        return this.name.equals(other.getName()) &&
                this.score == other.getScore();
    }

    /**
     * Compares the Player's score to another Player's score
     * @param other the player to be compared.
     * @return 1 if other's score is higher, -1 if other's score is lower. Returns result of two players' name
     * lexicographic comparison if scores are equal.
     */
    @Override
    public int compareTo(Player other) {
        if (this.score < other.score) return 1;
        if (this.score > other.score) return -1;
        return this.name.compareTo(other.getName());
    }
}
