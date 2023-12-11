import java.util.List;

public abstract class ARoom {

    protected String story;
    protected List<InteractiveObject> interactiveObjects;
    protected TextUI textUI;


   public ARoom(String story, List<InteractiveObject> interactiveObjects)
    {
        this.story = story;
        this.interactiveObjects = interactiveObjects;
    }


}
