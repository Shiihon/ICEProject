import java.util.Collections;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Room> rooms;
    private Player player;

    private boolean running;

    public Game() {
        this.rooms = new ArrayList<>();

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
        int choice = 0;

        options.add("New Game");
        options.add("Display top times");
        options.add("Quit");

        while (choice != options.indexOf("Quit")) {
            TextUI.displayMessage();
            choice = TextUI.getChoice("What would you like to do?", options);

            switch (choice) {
                case 0:
                    newGame();
                    return;
                case 1:
                    displayScores(loadScoreData());
                    break;
                case 2:
                    quit();
                    return;
            }
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

        if (playerName.equalsIgnoreCase("Sander er gud")) {
            TextUI.displaySuccesMessage("Congratulations!!! You have cleared the Game!");
            quit();
            return;
        }

        TextUI.displayMessage();
        TextUI.displayMessage("""
                Well %s, it's very nice to meet you.
                Unfortunately we're out of time. There's no turning back now.
                Good luck and try not to die.
                """.formatted(player.getName()));

        TextUI.getInput("Press Enter to continue...");
    }

    public void quit() {
        running = false;

        TextUI.displayMessage();
        TextUI.displayMessage("Quitting...");
    }

    public void runGameLoop() {
        int count = 0;

        while (running) {
            Room currentRoom = rooms.get(count);
            currentRoom.enter(player);

            if (!currentRoom.isComplete()) {
                TextUI.displayMessage();
                TextUI.displayMessage("Game Over.");
                break;
            }

            long currentScore = player.getTimeScore();
            long newScore = currentRoom.getTimeSpend() + currentRoom.getPenaltyTime();
            player.setTimeScore(currentScore + newScore);

            TextUI.displayMessage();
            TextUI.displayMessage("Time spend solving the room: " + convertSecondsToTime((int) currentRoom.getTimeSpend() / 1000));
            TextUI.displayMessage("Time including penalty time: " + convertSecondsToTime((int) newScore / 1000));
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
                TextUI.displayMessage();
                TextUI.displayMessage("Your final total time is: " + convertSecondsToTime((int) player.getTimeScore() / 1000));

                List<Player> players = loadScoreData();
                players.add(player);

                Collections.sort(players);

                while (players.size() > 5) {
                    players.removeLast();
                }

                displayScores(players);

                FileIO.saveScoreData("data/scoreData", players);
            }
            quit();
        }
    }

    private void displayScores(List<Player> players) {
        int count = 1;

        TextUI.displayMessage();
        TextUI.displayMessage("Top times:");

        for (Player player : players) {
            TextUI.displayMessage(String.format("%d: %-10s Time %s", count, player.getName(), convertSecondsToTime((int) player.getTimeScore() / 1000)));
            count++;
        }

        TextUI.getInput("Press Enter to continue...");
    }

    private List<Player> loadScoreData() {
        List<String> scoreData = FileIO.readScoreData("data/scoreData");
        List<Player> players = new LinkedList<>();

        if (!scoreData.isEmpty()) {
            for (String s : scoreData) {
                if (!s.trim().isEmpty()) {
                    String[] row = s.split(",");

                    String name = row[0].trim();
                    long timeScore = Long.parseLong(row[1].trim());

                    Player p = new Player(name);
                    p.setTimeScore(timeScore);

                    players.add(p);
                }
            }
        }

        Collections.sort(players);

        return players;
    }

    /**
     * <p>Convert seconds to a formatted time string</p><br>
     * <p>
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
