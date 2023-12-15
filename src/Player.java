public class Player {

    private final String name;
    private long timeScore;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getTimeScore() {
        return timeScore;
    }

    public void setTimeScore(long score) {
        this.timeScore = score;
    }

}
