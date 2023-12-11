import java.util.ArrayList;
import java.util.List;

public abstract class ARoom implements Room {

    protected String story;
    protected final List<InteractiveObject> interactiveObjects;

    public ARoom() {
        this.interactiveObjects = new ArrayList<>();
    }

    @Override
    public abstract void enter();

    @Override
    public abstract void exit();
}
