import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Room> rooms;
    private Player player;

    private boolean running;

    public Game() {
        this.rooms = new ArrayList<>();

        this.rooms.add(new StartRoom());
        this.rooms.add(new StartRoom());
        this.rooms.add(new StartRoom());
    }

    public void setup() {
        running = true;

        startMenu();
        runGameLoop();
    }

    public void startMenu() {
        TextUI.displayMessage();
        TextUI.displayMessage("Welcome to The Haunted Mansion - Escape Room");

        List<String> options = new ArrayList<>();
        options.add("New Game");
        options.add("Quit");

        int choice = TextUI.getChoice("What would you like to do?", options);

        switch (choice) {
            case 0:
                newGame();
                break;
            case 1:
                quit();
                break;
        }
    }

    public void newGame() {
        String playerName = TextUI.getInput("What's your name?");

        player = new Player(playerName);

        TextUI.displayMessage();
        TextUI.displayMessage("Welcome " + player.getPlayerName() + ", to The Haunted Mansion.");

        // Write story here
        TextUI.displayMessage("You find yourself deep in the woods and see an old mansion. you approach the mansion.");
    }

    public void quit() {
        TextUI.displayMessage();
        TextUI.displayMessage("Quitting...");
        running = false;
    }

    public void runGameLoop() {
        int count = 0;

        while (running) {
            rooms.get(count).enter();
            count++;

            if (count < rooms.size()) {
                String choice = TextUI.getChoiceYN("Do you wish to continue the game?");

                switch (choice) {
                    case "Y":
                        break;
                    case "N":
                        quit();
                }
            } else {
                TextUI.displayMessage();
                TextUI.displayMessage("You've completed The Haunted Mansion - Escape Room.");
                TextUI.displayMessage("Congratulations!");
                quit();
            }
        }
    }
}
