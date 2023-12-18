import java.util.LinkedHashMap;

public abstract class ARoom implements Room {

    protected final LinkedHashMap<InteractiveType, InteractiveObject> interactiveObjects;
    protected boolean isComplete;
    protected long penaltyTime;
    protected long startTime;
    protected long endTime;
    protected Player player;

    public ARoom() {
        this.interactiveObjects = new LinkedHashMap<>();
    }

    @Override
    public void enter(Player player) {
        this.player = player;

        penaltyTime = 0;
        startTime = System.currentTimeMillis();
        endTime = 0;
    }

    @Override
    public abstract void exit();

    @Override
    public boolean isComplete() {
        return isComplete;
    }

    public long getTimeSpend() {
        if (startTime > endTime) {
            return System.currentTimeMillis() - startTime;
        } else {
            return endTime - startTime;
        }
    }

    @Override
    public long getPenaltyTime() {
        return penaltyTime;
    }
}
