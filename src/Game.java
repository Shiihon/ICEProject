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
        TextUI.displayMessage();
        TextUI.displayMessage(
                """
                        Welcome, brave soul, to The Haunted Mansion – an escape room like no other.
                        An escape room that will test you, with different puzzles and obstacles.
                        A lot of things to investigate, discover and explore.
                                                
                        But be quick. Time is of the essence. Someone or something may be watching you, waiting...
                        """);

        TextUI.getInput("Press Enter to continue...");

        TextUI.displayMessage();
        TextUI.displayMessage("""
                Welcome to The Haunted Mansion - Escape Room.
                                
                I’m the Gamemaster and I will try my best to help you out where I can.
                But I don’t have all the answers. Some things you have to figure out on your own.
                In the mansion, you will find a lot of different items, clues and objects, remember them. They're important.
                Also, don't be stupid. Don't trust anyone and don’t investigate strange sounds coming from the dark.
                Or do it, see what happens hehe...
                The worst that can happen is you getting killed, no biggie.
                
                But before we go any further.
                """);

        String playerName = TextUI.getInput("What's your name?");
        player = new Player(playerName);

        TextUI.displayMessage();
        TextUI.displayMessage("Well " + player.getPlayerName() + ", there's no turning back now.");
        TextUI.displayMessage("""
                Good luck and try not to die %s
                """.formatted(playerName));

        TextUI.getInput("Press Enter to continue...");
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
                String choice = TextUI.getChoiceYN("Do you wish to continue?");

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
