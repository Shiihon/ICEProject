import java.util.LinkedHashMap;

public abstract class ARoom implements Room {

    protected String story;
    protected boolean isComplete;
    protected final LinkedHashMap<InteractiveType, InteractiveObject> interactiveObjects;

    public ARoom() {
        this.interactiveObjects = new LinkedHashMap<>();
    }

    @Override
    public abstract void enter();

    @Override
    public abstract void exit();

    @Override
    public boolean isComplete() {
        return isComplete;
    }
}
