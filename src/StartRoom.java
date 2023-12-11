import java.util.List;

public class StartRoom extends ARoom {
    private boolean candleLit = false;
    private boolean bookshelfPuzzleSolved = false;
    private boolean paintingPuzzleSolved = false;

    public StartRoom(String story, List<InteractiveObject> interactiveObjects) {
        super(story, interactiveObjects);
    }

    public void enter() {

    }

    public void runRoomLoop() {

    }

    private void startBookshelfPuzzle() {

    }

    private void startPaintingPuzzle() {

    }
    private void exit() {

    }
}
