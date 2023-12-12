import java.util.ArrayList;
import java.util.List;

public class StartRoom extends ARoom {

    private boolean candleLit;
    private boolean bookshelfPuzzleSolved;
    private boolean paintingPuzzleSolved;

    private boolean running;

    public StartRoom() {
        super();

        interactiveObjects.add(new InteractiveObject(InteractiveType.BOOKSHELF, "Bookshelf"));
        interactiveObjects.add(new InteractiveObject(InteractiveType.WALL_PAINTINGS, "Painting"));
        interactiveObjects.add(new InteractiveObject(InteractiveType.TABLE_CANDLE, "Candle"));
    }

    @Override
    public void enter() {
        System.out.println();
        System.out.println("You've entered the first room in the mansion.");

        System.out.println("You have entered the mansion. You see numerous objects in the entrance and a locked door.");

        runRoomLoop();
    }

    public void runRoomLoop() {
        running = true;

        while (running) {
            TextUI.displayMessage();
            TextUI.displayMessage("You see numerous objects in the entrance.");

            List<String> options = new ArrayList<>();

            for (InteractiveObject interactiveObject : interactiveObjects) {
                options.add("Approach the " + interactiveObject);
            }

            options.add("Exit");

            int choice = TextUI.getChoice("What would you like to do?", options);

            switch (choice) {
                case 0:
                    approachBookshelf();
                    break;
                case 1:
                    approachPainting();
                    break;
                case 2:
                    approachTableCandle();
                    break;
            }
        }
    }

    private void approachTableCandle() {
        TextUI.displayMessage();
        TextUI.displayMessage("You approach the table with an unlit candle on it.");

        List<String> options = new ArrayList<>();
        options.add("Light the candle.");
        options.add("Exit");

        int choice = TextUI.getChoice("What would you like to do?", options);

        switch (choice) {
            case 0:
                TextUI.displayMessage("You lit the candle and can now see your surroundings better");

                for (int i = interactiveObjects.size() - 1; i >= 0; i--) {
                    InteractiveObject interactiveObject = interactiveObjects.get(i);

                    if (interactiveObject.getType() == InteractiveType.TABLE_CANDLE) {
                        interactiveObjects.remove(interactiveObject);
                        break;
                    }
                }

                candleLit = true;
                break;
            case 1:
                TextUI.displayMessage("You step away from the table.");
                break;
        }
    }

    private void approachBookshelf() {

    }

    private void approachPainting() {

    }

    @Override
    public void exit() {
        System.out.println("Exit room");
    }
}
