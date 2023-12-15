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
        TextUI.displayMessage();
        TextUI.displayMessage("""
                Welcome, brave soul, to "The Haunted Mansion" – an escape room like no other.
                I’m the GameMaster and I will try to help you where I can. But I don’t have all the answers.
                There are some things you have to figure out on your own.
                                        
                You will be tested in different puzzles and obstacles.
                Objects and items will come up along the way. Remember them. They’re important.
                                        
                But don’t take too long.
                Time is of the essence, someone or something may be watching you, waiting...
                """);

        TextUI.getInput("Press Enter to continue...");

        TextUI.displayMessage();
        TextUI.displayMessage("""
                But before we begin.""");

        String playerName = TextUI.getInput("What's your name?");
        player = new Player(playerName);

        TextUI.displayMessage();
        TextUI.displayMessage("""
                Well %s, it's very nice to meet you.
                Unfortunately we're out of time. There's no turning back now.
                Good luck and try not to die.
                """.formatted(player.getName()));

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
            Room currentRoom = rooms.get(count);
            currentRoom.enter();

            if (!currentRoom.isComplete()) {
                TextUI.displayMessage();
                TextUI.displayMessage("Game Over.");
                break;
            }

            TextUI.displayMessage();
            TextUI.displayMessage("Time spend solving the room: " + convertSecondsToTime((int) currentRoom.getTimeSpend() / 1000));
            TextUI.getInput("Press Enter to continue...");

            count++;

            if (count < rooms.size()) {
                TextUI.displayMessage();
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

    /**
     * Convert seconds to a formatted time string
     * Source: <a href="https://stackoverflow.com/a/6118983">Stack Overflow</a>
     *
     * @param totalSeconds The total seconds to be formatted
     * @return The formatted time
     */
    public String convertSecondsToTime(int totalSeconds) {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
