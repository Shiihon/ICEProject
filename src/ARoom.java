import java.util.ArrayList;
import java.util.List;

public abstract class ARoom {

    protected String story;
    protected final List<InteractiveObject> interactiveObjects;

    public ARoom() {
        this.interactiveObjects = new ArrayList<>();
    }
}
