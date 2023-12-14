import java.util.ArrayList;
import java.util.List;

public class StartRoom extends ARoom {

    private boolean candleLit;
    private boolean hasBlanket;
    private boolean riddleSolved;

    private boolean running;

    public StartRoom() {
        super();

        interactiveObjects.add(new InteractiveObject(InteractiveType.BOOKSHELF, "Bookshelf"));
        interactiveObjects.add(new InteractiveObject(InteractiveType.WALL_PAINTINGS, "Painting"));
        interactiveObjects.add(new InteractiveObject(InteractiveType.TABLE_CANDLE, "Candle"));
    }

    @Override
    public void enter() {
        TextUI.displayMessage();
        TextUI.displayMessage();
        TextUI.displayMessage("""
                You hear a loud noise and suddenly everything goes dark. You wake up feeling dizzy.
                As you wake up you try to look around. It’s dark and you can’t really make out anything.
                But you appear to be in an old house of some sorts. It appears you were teleported, somewhere, somehow.
                You’re not quite sure where you are but one thing’s for sure.
                It’s cold. Almost freezing.
                """);

        TextUI.getInput("Press Enter to continue...");

        TextUI.displayMessage();
        TextUI.displayMessage("""
                Suddenly you hear a voice coming from the room. It sounds like a little girl, crying.
                
                "So cold... it’s... so cold..."
                """);

        TextUI.getInput("Press Enter to continue...");

        TextUI.displayMessage();
        TextUI.displayMessage("""
                You get on your feet, your eyes are getting used to the dark but you still can’t make out any details.
                You look around and try to orient yourself…
                
                You're in a big room filled with old furniture, paintings, and a faint smell of blood.
                You see a table in the middle of the room with what looks to be a standing candle on it.
                In the corner of the room is a large, tall rectangular object, a bookshelf maybe?
                To your right, there’s something on the wall, paintings.
                In the opposite end of the room you see a door
                And in the right corner of the room, is what looks to be a... a... human, a girl? Or whatever it is, it’s crying.
                """);

        runRoomLoop();
    }

    public void runRoomLoop() {
        running = true;

        while (running) {
            TextUI.displayMessage();

            List<String> options = new ArrayList<>();

            for (InteractiveObject interactiveObject : interactiveObjects) {
                options.add("Approach the " + interactiveObject);
            }

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
                TextUI.displayMessage();
                TextUI.displayMessage("You lit the candle and can now see your surroundings better");
                TextUI.getInput("Press Enter to continue...");

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
                TextUI.displayMessage();
                TextUI.displayMessage("You step away from the table.");
                TextUI.getInput("Press Enter to continue...");
                break;
        }
    }

    private void approachBookshelf() {
        TextUI.displayMessage();
        TextUI.displayMessage("You begin walking to the big bookshelf");

        if (!candleLit) {
            TextUI.displayMessage("You see a lot of books on the shelf but you can't read and not even make out any of the titles.");

            TextUI.getInput("Press Enter to continue...");
            return;
        }

        TextUI.displayMessage("You see different books in the shelf. At the top of the shelf there is a sign with “Horror” written on it");
        TextUI.displayMessage("You quickly realize that all of the books are old and new horror movies, which makes you question why.");
        TextUI.displayMessage("There are some that catch your eye more than others.");

        TextUI.getInput("Press enter to continue...");
        List<String> options = new ArrayList<>();

        options.add("The Exorcist");
        options.add("Scream");
        options.add("The nun");
        options.add("It");
        options.add("Saw");
        options.add("Smile");
        options.add("American Psycho");
        options.add("Sinister");
        options.add("Take a step back");

        int choice = TextUI.getChoice("What do you want to do?", options);

        if (choice == options.indexOf("Smile")) {
            if (!riddleSolved) {
                TextUI.displayMessage();
                TextUI.displayMessage("You flip through the pages and see a message: ");
                approachRiddle(true);

                if (riddleSolved) {
                    TextUI.displayMessage();
                    TextUI.displayMessage("You say the word out loud, and you are suddenly startled with a loud noise!");
                    TextUI.displayMessage("The bookshelf starts moving. The bookshelf has now revealed a hidden numberpad");
                    TextUI.getInput("Press Enter to continue...");
                }
            }
            if (riddleSolved) {
                approachNumberpad();
            }
        } else if (choice != options.indexOf("Take a step back")) {
            TextUI.displayMessage("You take " + options.get(choice) + " the book is full of dust. You start going through the pages reading the story, but nothing happens.");
            TextUI.displayMessage("You put the book back on the shelf, take a step back, you're looking at the bookshelf again.");
            approachBookshelf();
        }
    }

    private void approachRiddle(boolean firstTime) {
        TextUI.displayMessage("I'm always hungry, I must be fed. The finger I touch will soon turn red. What am I?");

        int choice = 0;

        if (firstTime) {
            List<String> options = new ArrayList<>();
            options.add("Try guessing riddle");
            options.add("Take a step back");

            choice = TextUI.getChoice("What do you want to do?", options);
        }

        if (choice == 0) {
            String answer = TextUI.getInput("Your guess: ").trim();

            if (answer.equalsIgnoreCase("Fire")) {
                riddleSolved = true;
            } else {
                List<String> options = new ArrayList<>();
                options.add("Try again");
                options.add("Take a step back");

                choice = TextUI.getChoice("What do you want to do?", options);

                if (choice == 0) {
                    approachRiddle(false);
                }
            }
        }
    }

    private void approachPainting() {

    }

    private void approachNumberpad() {

        TextUI.displayMessage("You take a step closer to the number pad, there is number form 1-9 and a green button on it, with the word “confirm” on it.");
        TextUI.displayMessage("You see there are some options, but don’t really know what to do or what’s gonna happen.");
        TextUI.displayMessage("Or what will be if you press the wrong numbers, so you decide not to do anything right now and look around again.\");");
        TextUI.getInput("Press enter to continue...");

        List<String> options = new ArrayList<>();
        options.add("Use the numberpad");
        options.add("Take a step back");

        int input = TextUI.getChoice("What do you want to do", options);

        interactNumberpad(input, options);

    }

    private void interactNumberpad(int input, List<String> options) {

        if (input == options.indexOf("Use the numberpad")) {

            int codeinput = TextUI.getNumericInput("Enter the code, then press enter....");

            if (codeinput == 69420) {
                TextUI.displayMessage("The green light is flashing and you hear a loud popping sound of a hidden safe getting opened");
                TextUI.getInput("Press enter to continue...");
                TextUI.displayMessage("You investigate the safe and find an old blanket. It's very thick and warm, and could keep anyone happy should they be wrapped around it");
                hasBlanket = true;

            } else {
                TextUI.displayMessage("Nothings happens.....");
                List<String> numberpadOptions = new ArrayList<>();

                numberpadOptions.add("Try again");
                numberpadOptions.add("Take a step back");

                int numberInput = TextUI.getChoice("What you want to do", numberpadOptions);
                if (numberInput == numberpadOptions.indexOf("Try again")) {
                    interactNumberpad(input, options);
                }
            }
        }
    }

    @Override
    public void exit() {
        TextUI.displayMessage("Exit room");
    }
}
