import java.util.ArrayList;
import java.util.List;

public class StartRoom extends ARoom {

    private boolean candleLit;
    private boolean hasBlanket;
    private boolean riddleSolved;
    private boolean running;

    public StartRoom() {
        super();

        interactiveObjects.put(InteractiveType.BOOKSHELF, new InteractiveObject(InteractiveType.BOOKSHELF, "Bookshelf"));
        interactiveObjects.put(InteractiveType.WALL_PAINTINGS, new InteractiveObject(InteractiveType.WALL_PAINTINGS, "Painting"));
        interactiveObjects.put(InteractiveType.TABLE_WITH_CANDLE, new InteractiveObject(InteractiveType.TABLE_WITH_CANDLE, "Candle"));
        interactiveObjects.put(InteractiveType.LITTLE_GIRL, new InteractiveObject(InteractiveType.LITTLE_GIRL, "Little girl"));
    }

    @Override
    public void enter() {
        TextUI.displayMessage();
        TextUI.displayMessage();
        TextUI.displayMessage("""
                You hear a loud noise and suddenly everything goes dark. You wake in a dark room feeling dizzy.
                You can’t see much but you appear to be in an old house of some sorts.
                It seems that you’ve been teleported, somewhere, somehow. You’re not quite sure where you are but one thing’s for sure.
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
                You get on your feet, and try to look around in the dark.
                The room is filled with old furniture and paintings… and a faint smell of blood.
                                
                In the middle of the room is a table with what looks to be a standing candle on it.
                To your right, are some paintings on the wall but you can’t make out any details.
                In the corner of the room you see a large, tall rectangular object, a bookshelf maybe?
                At the opposite end of the room you see a… a human, a little girl? Or whatever it is. It’s crying.
                Behind the little girl you see a door. A way out maybe.
                """);
        TextUI.getInput("Press Enter to continue...");

        runRoomLoop();
    }

    public void runRoomLoop() {
        running = true;

        while (running) {
            TextUI.displayMessage();

            List<InteractiveObject> interactiveObjectList = new ArrayList<>(interactiveObjects.values());
            List<String> options = new ArrayList<>();

            for (InteractiveObject interactiveObject : interactiveObjectList) {
                options.add("Approach the " + interactiveObject);
            }

            int choice = TextUI.getChoice("What would you like to do?", options);

            InteractiveObject interactiveObject = interactiveObjectList.get(choice);

            switch (interactiveObject.getType()) {
                case InteractiveType.BOOKSHELF:
                    TextUI.displayMessage();
                    TextUI.displayMessage("You begin to walk slowly towards the table, trying not to trip over anything on the floor.");
                    approachBookshelf();
                    break;
                case InteractiveType.WALL_PAINTINGS:
                    approachPainting();
                    break;
                case InteractiveType.TABLE_WITH_CANDLE:
                    approachTableCandle();
                    break;
                case InteractiveType.LITTLE_GIRL:
                    approachGirl();
                    break;
            }
        }
    }

    private void approachTableCandle() {
        TextUI.displayMessage();
        TextUI.displayMessage();
        TextUI.displayMessage("""
                You begin to walk slowly towards the table, trying not to trip over anything on the floor.
                                
                Coming to the table you see a small metal object next to the standing candle. A lighter.
                It’s looks old but still functional.
                """);

        List<String> options = new ArrayList<>();
        options.add("Light the candle.");
        options.add("Exit");

        int choice = TextUI.getChoice("What would you like to do?", options);

        switch (choice) {
            case 0:
                interactiveObjects.remove(InteractiveType.TABLE_WITH_CANDLE);
                candleLit = true;

                TextUI.displayMessage();
                TextUI.displayMessage("""
                        You light the candle and watch as it begins to light up the room.
                        Suddenly you can see everything a little better.
                        """);
                TextUI.getInput("Press Enter to continue...");
                break;
            case 1:
                TextUI.displayMessage();
                TextUI.displayMessage("""
                        You step back from the table.
                        """);
                TextUI.getInput("Press Enter to continue...");
                break;
        }
    }

    private void approachBookshelf() {
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
            if (hasBlanket) {
                TextUI.displayMessage();
                TextUI.displayMessage("Nothing new happened.");
                TextUI.getInput("Press Enter to continue...");
                approachBookshelf();
                return;
            }

            if (!riddleSolved) {
                TextUI.displayMessage();
                TextUI.displayMessage("You flip through the pages and see a message: ");
                approachRiddle(true);

                if (riddleSolved) {
                    TextUI.displayMessage();
                    TextUI.displaySuccesMessage("You say the word out loud, and you are suddenly startled with a loud noise!");
                    TextUI.displaySuccesMessage("The bookshelf starts moving. The bookshelf has now revealed a hidden numberpad");
                    TextUI.getInput("Press Enter to continue...");
                }
            }
            if (riddleSolved) {
                approachNumberpad();
            }
        } else if (choice != options.indexOf("Take a step back")) {
            TextUI.displayMessage("You take " + options.get(choice) + " the book is full of dust. You start going through the pages reading the story, but nothing happens.");
            TextUI.displayMessage("You put the book back on the shelf, take a step back, you're looking at the bookshelf again.");
            TextUI.getInput("Press Enter to continue...");
            approachBookshelf();
        }
    }

    private void approachRiddle(boolean firstTime) {
        TextUI.displayRiddle("I'm always hungry, I must be fed. The finger I touch will soon turn red. What am I?");

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
                TextUI.displaySuccesMessage("The green light is flashing and you hear a loud popping sound of a hidden safe getting opened");
                TextUI.getInput("Press enter to continue...");
                TextUI.displaySuccesMessage("You investigate the safe and find an old blanket. It's very thick and warm, and could keep anyone happy should they be wrapped around it");
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

    private void approachPainting() {
        TextUI.displayMessage();
        TextUI.displayMessage("You begin walking towards the wall where the paintings are hanging");

        if (!candleLit) {
            TextUI.displayMessage("You see 5 paintings on the wall making a circle, but it’s too dark to get a proper look.");
            TextUI.displayMessage("Maybe you could find something in the room to illuminate the place a little more");
            TextUI.getInput("Press enter to continue");
            return;
        }

        TextUI.displayMessage("You see 5 paintings on the wall making a circle, all of them are different. They all have different people on it, doing different things.");
        TextUI.displayMessage("The only thing they have in common is that they are all smiling. No matter what they are doing or what's happening on the painting, the people are smiling.");
        TextUI.getInput("Press enter to continue");

        TextUI.displayMessage("In the middle of the paintings, there is a note: ");
        paintingRiddle();
    }

    private void paintingRiddle() {

        TextUI.displayRiddle("What is the highest number between 1 and 1.000.000 that don't have the letter “N” when you say it out loud?");
        TextUI.displayRiddle("Take that number and subtract 19 from the number." + "\nRemember this number.");
        TextUI.displayRiddle("Now take the number and add 351 to the number.");
        TextUI.displayRiddle("You should have a two-digit number and three-digit number");
        TextUI.displayRiddle("put them in the order you got them to make a five-digit number and remember it");
        TextUI.getInput("Press enter to continue");

        TextUI.displaySuccesMessage("You’ve now acquired a five-digit number. It looks like there’s nothing more you can do with the paintings.");
        TextUI.getInput("Press enter to continue");
        TextUI.displayErrorMessage("Or maybe you did the math wrong, only time will tell...");
        TextUI.getInput("Press enter to continue");
    }

    private void approachGirl() {
        TextUI.displayMessage();
        TextUI.displayMessage("You begin walking towards the little girl standing in front of the door");

        if (!candleLit) {
            TextUI.displayMessage("You can hear her crying louder and louder meanwhile the smell of blood is getting worse and worse.");
            TextUI.getInput("Press enter to continue");
            TextUI.displayMessage("You're getting closer, and you're about 3 steps away from the girl when she...");
            TextUI.getInput("Press enter to continue");
            TextUI.displayErrorMessage("suddenly jumps on you with a knife. She hits you in the throat all while smiling at you. Everything goes dark.");
            TextUI.displayErrorMessage("You died, noob");
            TextUI.getInput("Press enter to continue");
            TextUI.displayRiddle("*** Maybe a little light would help you in the darkness next time ***");

            running = false;
        } else {
            TextUI.displayMessage("You can hear her crying louder and louder meanwhile the smell of blood is getting worse and worse.");
            TextUI.displayMessage("The candle casts a small light on her, you see blood all around her.");
            TextUI.getInput("Press enter to continue");
            TextUI.displayRiddle("You see a suble smile even though she is shivering and you can hear her teeth chatter. She is freezing...");
            TextUI.getInput("Press enter continue");
            TextUI.displayMessage("you try calling out to her, but get no answer.");
            TextUI.displayMessage("You feel like something isn’t right. She’s got something in her hand, but you can't make out what it is.");
            TextUI.getInput("Press enter to continue");

            ArrayList<String> options = new ArrayList<>();
            options.add("Go closer");
            options.add("Take a step back");
            if (hasBlanket) {
                options.add("Give her the blanket");
            }

            int input = TextUI.getChoice("What would you like to do", options);

            if (input == options.indexOf("Go closer")) {
                TextUI.displayMessage("You're getting closer, and you're about 3 steps away from the girl when she...");
                TextUI.getInput("Press enter to continue");
                TextUI.displayErrorMessage("suddenly jumps on you with a knife. She hits you in the throat all while smiling at you. Everything goes dark.");
                TextUI.displayErrorMessage("You died, noob");
                TextUI.getInput("Press enter to continue");
                TextUI.displayRiddle("*** Maybe she is more friendly when she is warm ***");

                running = false;
            } else if (hasBlanket && input == options.indexOf("Give her the blanket")) {
                TextUI.displayMessage("You give her the blanket. She moves away from the door.");
                TextUI.displayMessage("You've opened the door and now have acces to he next room.");
                TextUI.displaySuccesMessage("Congratulations!!! You have cleared the first room!");

                isComplete = true;
                running = false;
            }
        }
    }

    @Override
    public void exit() {
        TextUI.displayMessage("Exit room");
    }
}