public class Player implements Comparable<Player> {

    private final String name;
    private long timeScore;

    public Player(String name) {
        this.name = name;
    }

    /**
     * Get the name of the player
     *
     * @return The name of the player
     */
    public String getName() {
        return name;
    }

    public long getTimeScore() {
        return timeScore;
    }

    public void setTimeScore(long score) {
        this.timeScore = score;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.name, this.timeScore);
    }

    @Override
    public int compareTo(Player other) {
        return Long.compare(timeScore, other.getTimeScore());
    }
}
