import java.util.LinkedHashMap;

public abstract class ARoom implements Room {

    protected boolean isComplete;
    protected long penaltyTime;
    protected long startTime;
    protected long endTime;

    protected final LinkedHashMap<InteractiveType, InteractiveObject> interactiveObjects;

    public ARoom() {
        this.interactiveObjects = new LinkedHashMap<>();
    }

    @Override
    public abstract void enter(Player player);

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
