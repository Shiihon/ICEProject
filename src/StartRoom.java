import java.util.ArrayList;
import java.util.List;

public class StartRoom extends ARoom {

    private static final long HINT_PENALTY_TIME = 5000;
    private boolean hasWalkieTalkie;
    private boolean riddleSolved;
    private boolean hasBlanket;
    private boolean candleLit;
    private boolean running;
    private Player player;

    public StartRoom() {
        super();

        interactiveObjects.put(InteractiveType.BOOKSHELF, new InteractiveObject(InteractiveType.BOOKSHELF, "Bookshelf"));
        interactiveObjects.put(InteractiveType.WALL_PAINTINGS, new InteractiveObject(InteractiveType.WALL_PAINTINGS, "Painting"));
        interactiveObjects.put(InteractiveType.TABLE, new InteractiveObject(InteractiveType.TABLE, "Candle"));
        interactiveObjects.put(InteractiveType.LITTLE_GIRL, new InteractiveObject(InteractiveType.LITTLE_GIRL, "Little girl"));
    }

    @Override
    public void enter(Player player) {
        this.player = player;
        startTime = System.currentTimeMillis();
        endTime = 0;
        penaltyTime = 0;

        TextUI.displayMessage();
        TextUI.displayMessage();
        TextUI.displayMessage("""
                You hear a loud noise and suddenly everything goes dark.
                                
                You wake in a dark room feeling dizzy.
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
                                
                In the middle of the room is a table with what looks to be a standing candle on it and maybe something next to it.
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
            List<InteractiveObject> interactiveObjectList = new ArrayList<>(interactiveObjects.values());
            List<String> options = new ArrayList<>();

            for (InteractiveObject interactiveObject : interactiveObjectList) {
                options.add("Approach the " + interactiveObject);
            }

            TextUI.displayMessage();
            TextUI.displayMessage();
            int choice = TextUI.getChoice("What would you like to do?", options);

            InteractiveObject interactiveObject = interactiveObjectList.get(choice);

            switch (interactiveObject.getType()) {
                case InteractiveType.BOOKSHELF:
                    approachBookshelf();
                    break;
                case InteractiveType.WALL_PAINTINGS:
                    approachPainting();
                    break;
                case InteractiveType.TABLE:
                    approachTable();
                    break;
                case InteractiveType.LITTLE_GIRL:
                    approachGirl();
                    break;
            }
        }
    }

    private void approachTable() {
        TextUI.displayMessage();
        TextUI.displayMessage();

        if (!candleLit) {
            TextUI.displayMessage("""
                    You begin to slowly walk towards the table, trying not to trip over anything on the floor.
                        
                    Coming to the table you see a small metal object next to the standing candle. A lighter.
                    It’s looks old but still functional.
                    """);

            List<String> options = new ArrayList<>();
            options.add("Light the candle.");
            options.add("Exit");

            TextUI.displayMessage();
            int choice = TextUI.getChoice("What would you like to do?", options);

            TextUI.displayMessage();
            switch (choice) {
                case 0:
                    candleLit = true;

                    TextUI.displayMessage("""
                            You light the candle and watch as it begins to light up the room.
                            Suddenly you can see everything a little better.
                            """);
                    break;
                case 1:
                    TextUI.displayMessage("""
                            You step back from the table.
                            """);
            }

            TextUI.getInput("Press Enter to continue...");
        } else {
            TextUI.displayMessage("""
                    You walk towards the table.
                    """);
        }

        if (hasWalkieTalkie) {
            TextUI.displayMessage("""
                    Nothing changed since the last time you were here.
                    """);
            TextUI.getInput("Press Enter to continue...");
            return;
        }

        if (candleLit) {
            TextUI.displayMessage();
            TextUI.displayMessage("""
                    On the table you notice a walkie talkie on the edge of the table.
                    Looking at the walkie talkie you notice a small sticky note on the bottom. “Gamemaster”.
                    You pick up the walkie talkie and immediately a voice begins speaking through.
                    """);
            TextUI.getInput("Press Enter to continue...");

            TextUI.displayMessage();
            TextUI.displayMessage("""
                    "Ayyy %s, how are you? I see you’re not dead yet, that’s good.
                    Listen, this walkie talkie is the only way for me to get into contact with you.
                    So if you want my help during the game, you gotta put the walkie talkie in your pocket.
                    Do notice that using the walkie talkie will cost you time.
                    Or you can be a bad bitch and leave me here, fly solo, play this on hardmode #iRespectTheGame”
                    """.formatted(player.getName()));
            TextUI.getInput("Press Enter to continue...");

            List<String> options = new ArrayList<>();
            options.add("Put the walkie talkie in your pocket");
            options.add("Fly solo");

            TextUI.displayMessage();
            int choice = TextUI.getChoice("What would you like to do?", options);

            TextUI.displayMessage();
            switch (choice) {
                case 0:
                    hasWalkieTalkie = true;

                    TextUI.displayMessage("""
                            You put the walkie talkie in your pocket.
                                                        
                            "If you need help with anything, use the walkie talkie.
                            Gamemaster, signing out."
                            """);
                    break;
                case 1:
                    TextUI.displayMessage("""
                            “I respect the decision. The walkie talkie will remain on the table if you change your mind.
                            Good luck.”
                            """);
            }

            TextUI.getInput("Press Enter to continue...");
        }
    }

    private void approachBookshelf() {
        TextUI.displayMessage();
        TextUI.displayMessage();
        TextUI.displayMessage("You begin walking towards the large bookshelf.");

        if (!candleLit) {
            TextUI.displayMessage("""
                    You see a lot of books on the shelf but it’s too dark in the room to make out any of the titles.
                    Maybe something in the room could help to illuminate the place.
                    """);

            TextUI.getInput("Press Enter to continue...");
            return;
        }

        TextUI.displayMessage("""
                On the large bookshelf you see a lot of different titles: “IT, Saw, American Psycho, Sinister”. At the top of the shelf is a sign, “Horror”
                You quickly realize that all the books are old and new Horror titles and you ask yourself “Why”?
                Some of the books are very old, others more recent.
                """);
        TextUI.getInput("Press Enter to continue...");

        chooseBook();
    }

    private void chooseBook() {
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

        if (hasWalkieTalkie) {
            options.add("Get hint");
        }

        TextUI.displayMessage();
        int choice = TextUI.getChoice("What book would you like to look at? Or take a step back:", options);

        if (choice == options.indexOf("Smile")) {
            if (hasBlanket) {
                TextUI.displayMessage();
                TextUI.displayMessage("""
                        Nothing new happened.
                        """);
                TextUI.getInput("Press Enter to continue...");
                return;
            }

            if (!riddleSolved) {
                TextUI.displayMessage();
                TextUI.displayMessage("You flip through the pages and notice a strange message:");
                approachRiddle();

                if (riddleSolved) {
                    TextUI.displayMessage();
                    TextUI.displaySuccesMessage("""
                            You say the word out loud, and you're suddenly startled with a loud noise!
                            The bookshelf begins to move. After a short while, a keypad behind the bookshelf has been revealed.
                            """);

                    TextUI.getInput("Press Enter to continue...");
                }
            }
            if (riddleSolved) {
                approachKeypad();
            }
        } else if (choice == options.indexOf("Get hint")) {
            penaltyTime += HINT_PENALTY_TIME;

            TextUI.displayMessage();
            TextUI.displayMessage("""
                    Maybe something else in the room could give you a clue?
                    Perhaps those paintings on the wall over there.
                    """);
            TextUI.getInput("Press Enter to continue...");

            options.clear();
            options.add("Look at a book");
            options.add("Take a step back");

            TextUI.displayMessage();
            choice = TextUI.getChoice("What would you like to do?", options);

            if (choice == 0) {
                chooseBook();
            }
        } else if (choice != options.indexOf("Take a step back")) {
            TextUI.displayMessage();
            TextUI.displayMessage("""
                    You take the book '%s' out of the bookshelf.
                    It’s full of dust. Safe to say it’s been here for a while. You begin reading through the pages, flipping through each one.
                    After a while you flip the book over to take a look at the front cover, trying to see if anything catches your eye.
                    But nothing. After a while you notice that nothing has happened. You put the book back on the shelf.
                    """.formatted(options.get(choice)));
            TextUI.getInput("Press Enter to continue...");

            chooseBook();
        }
    }

    private void approachRiddle() {
        List<String> options = new ArrayList<>();
        boolean tryAgain = false;
        int choice = 0;

        options.add("Try to guess the riddle");
        options.add("Take a step back");

        if (hasWalkieTalkie) {
            options.add("Get hint");
        }

        while (choice != options.indexOf("Take a step back")) {
            if (!tryAgain) {
                TextUI.displayRiddle("*I'm always hungry, I must be fed. The finger I touch will soon turn red. What am I?*");

                options.set(0, "Try to guess the riddle");

                TextUI.displayMessage();
                choice = TextUI.getChoice("Wat do you want to do?", options);
            } else {
                options.set(0, "Try again");

                choice = TextUI.getChoice("Wat do you want to do?", options);

                if (choice == 0) {
                    TextUI.displayMessage();
                    TextUI.displayRiddle("*I'm always hungry, I must be fed. The finger I touch will soon turn red. What am I?*");
                }
            }

            if (choice == 0) {
                TextUI.displayMessage();
                String answer = TextUI.getInput("Your guess:").trim();

                if (answer.equalsIgnoreCase("Fire")) {
                    riddleSolved = true;
                    break;
                } else {
                    TextUI.displayMessage();
                    TextUI.displayErrorMessage("""
                            You say the word out loud but nothing happened.
                            """);

                    tryAgain = true;
                }
            } else if (choice == options.indexOf("Get hint")) {
                penaltyTime += HINT_PENALTY_TIME;

                TextUI.displayMessage();
                TextUI.displayMessage("""
                        Hmm. Turn red? Maybe it's something hot?
                        """);
                TextUI.getInput("Press Enter to continue...");
                TextUI.displayMessage();

                tryAgain = false;
            }
        }
    }

    private void approachKeypad() {
        TextUI.displayMessage();
        TextUI.displayMessage("""
                You take a step closer to the keypad.
                                    
                On the keypad you see buttons with the numbers 0-9 and a green button on the right with the word “Confirm” on it.
                                    
                Next to the keypad you see an old metal safe built into the wall.
                It looks impenetrable. Definitely too strong to break open.
                """);
        TextUI.getInput("Press Enter to continue...");

        List<String> options = new ArrayList<>();
        boolean tryAgain = false;
        int choice = 0;

        options.add("Use keypad");
        options.add("Take a step back");

        if (hasWalkieTalkie) {
            options.add("Get hint");
        }

        while (choice != options.indexOf("Take a step back")) {
            if (!tryAgain) {
                options.set(0, "Use keypad");
            } else {
                options.set(0, "Try again");
            }

            TextUI.displayMessage();
            choice = TextUI.getChoice("What would you like to do?", options);

            if (choice == 0) {
                TextUI.displayMessage();
                int input = TextUI.getNumericInput("Enter the code:");

                if (input == 69420) {
                    hasBlanket = true;

                    TextUI.displayMessage();
                    TextUI.displaySuccesMessage("""
                            A green light next to the keypad lights up and you hear the loud sound of the old safe opening.
                            You investigate the old safe and find a blanket inside.
                            It looks very warm. Sure to warm up anyone.
                            """);
                    TextUI.getInput("Press Enter to continue...");
                    break;
                } else {
                    TextUI.displayMessage();
                    TextUI.displayErrorMessage("""
                            A red light next to the keypad lights up.
                                                    
                            Nothings happened...
                            """);
                    TextUI.getInput("Press Enter to continue...");

                    tryAgain = true;
                }
            } else if (choice == options.indexOf("Get hint")) {
                penaltyTime += HINT_PENALTY_TIME;

                TextUI.displayMessage();
                TextUI.displayMessage("""
                        Maybe something else in the room could give you a clue?
                        Perhaps those paintings on the wall over there.
                        There appears to be some kind of note in the middle of them?
                        """);
                TextUI.getInput("Press Enter to continue...");

                tryAgain = false;
            }
        }
    }

    private void approachPainting() {
        TextUI.displayMessage();
        TextUI.displayMessage();
        TextUI.displayMessage("You begin walking towards the paintings on the wall.");

        if (!candleLit) {
            TextUI.displayMessage();
            TextUI.displayMessage("""
                    You see the siloed of 5 paintings on the wall, marking a circle. But it’s too dark to see what's on them.
                    Maybe something in the room could help you illuminate the place a little more.
                    """);
            TextUI.getInput("Press Enter to continue...");
            return;
        }

        TextUI.displayMessage();
        TextUI.displayMessage("""
                You take a look at the 5 paintings on the wall. All of them depict something different.
                Different people, doing different things.
                                
                They do have one thing in common though. They're all smiling.
                No matter what they're doing or what's happening in the painting, the people are smiling.
                """);
        TextUI.getInput("Press Enter to continue...");

        TextUI.displayMessage();
        TextUI.displayMessage("In the middle of the paintings, you see a note:");
        paintingRiddle();
    }

    private void paintingRiddle() {
        TextUI.displayRiddle("""
                What is the highest number between 1 and 1.000.000 that does not contain the letter “N” when said out loud?
                Take that number and subtract 19 from it.
                Remember the result.
                                
                Now take the number and add 351 to it.
                Remember the result.
                                
                You should now have a two-digit number and a three-digit number.
                Put them together in the order you got them to make a five-digit number.
                Remember this final number. You'll need it.
                """);

        TextUI.getInput("Press Enter to continue...");

        TextUI.displaySuccesMessage("""
                With that done you’ve now acquired a five-digit number.
                It looks like that's about all you can do with the paintings.
                                
                Maybe these clues you've gathered can be used somewhere else?
                        """);

        TextUI.getInput("Press Enter to continue...");
    }

    private void approachGirl() {
        TextUI.displayMessage();
        TextUI.displayMessage();

        TextUI.displayMessage("""
                You begin to slowly walk towards the little girl standing in front of the door.
                She has buried her face in her hands.
                """);
        TextUI.getInput("Press Enter to continue...");

        if (!candleLit) {
            approachGirlDeath();
            return;
        } else {
            TextUI.displayMessage();
            TextUI.displayMessage("""
                    The candle casts a faint light on her.
                    You notice she's standing in a puddle of blood.
                    """);
            TextUI.getInput("Press Enter to continue...");

            TextUI.displayMessage();
            TextUI.displayRiddle("""
                    You can't make out her face. But it looks like she's... smiling?
                    The girl is shivering and you can hear her teeth chatter. She's clearly freezing.
                    And yet... she continues to smile.
                    """);
            TextUI.getInput("Press Enter continue...");

            TextUI.displayMessage();
            TextUI.displayMessage("""
                    You try calling out to her, but no answer...
                    Something doesn't feel right. She’s got something in her hand. Something shiny.
                    But you can't make out what it is.
                    """);
            TextUI.getInput("Press Enter to continue...");
        }

        List<String> options = new ArrayList<>();
        int choice = 0;

        options.add("Go closer");

        if (hasBlanket) {
            options.add("Give her the blanket");
        }

        options.add("Take a step back");

        if (hasWalkieTalkie) {
            options.add("Get hint");
        }

        while (choice != options.indexOf("Take a step back")) {
            TextUI.displayMessage();
            choice = TextUI.getChoice("What would you like to do?", options);

            if (choice == 0) {
                approachGirlDeath();
                break;
            } else if (choice == options.indexOf("Give her the blanket")) {
                girlRiddle();

                TextUI.displayRiddle("The girl stops smiling at you, she looks normal now.");
                TextUI.displayRiddle("She moves away form the door");
                TextUI.getInput("Press Enter to continue...");

                TextUI.displayMessage();
                TextUI.displayMessage("""
                        You open the door and can now leave to the next room.
                        """);

                isComplete = true;
                exit();
                break;
            } else if (choice == options.indexOf("Get hint")) {
                penaltyTime += HINT_PENALTY_TIME;

                TextUI.displayMessage();
                TextUI.displayMessage("""
                        The girl looks cold. Maybe you could find something to help her stay warm?
                        """);
                TextUI.getInput("Press Enter to continue...");
            }
        }
    }

    private void approachGirlDeath() {
        TextUI.displayMessage();

        if (!candleLit) {
            TextUI.displayMessage("It's too dark to see so you attempt to get closer.");
        }

        TextUI.displayMessage("""
                Suddenly she begins to cry. Louder and louder, meanwhile the smell of blood is getting worse.
                You're now about 3 steps away from the girl when...
                """);
        TextUI.getInput("Press Enter to continue...");

        TextUI.displayMessage();
        TextUI.displayErrorMessage("""
                She jumps on you with a knife, slicing your throat, all while smiling at you.
                As your lifeless body falls to the ground, the little girl continues to smile.
                                    
                You died. Noob
                """);
        TextUI.getInput("Press Enter to continue...");

        if (!candleLit) {
            TextUI.displayMessage();
            TextUI.displayRiddle("*** Maybe a little light could've helped you in the darkness ***");
        } else {
            TextUI.displayMessage();
            TextUI.displayRiddle("*** Maybe she's more friendly when she's warm ***");
        }

        exit();
    }

    private void girlRiddle() {
        List<String> options = new ArrayList<>();
        boolean riddleSolved = false;
        boolean tryAgain = false;
        int choice;

        options.add("Try to guess the riddle");
        options.add("Get hint");

        while (!riddleSolved) {
            if (!tryAgain) {
                TextUI.displayRiddle("*What has keys, but can't open anything?*");

                options.set(0, "Try to guess the riddle");

                TextUI.displayMessage();
                choice = TextUI.getChoice("Wat do you want to do?", options);
            } else {
                options.set(0, "Try again");

                choice = TextUI.getChoice("Wat do you want to do?", options);

                if (choice == 0) {
                    TextUI.displayMessage();
                    TextUI.displayRiddle("*What has keys, but can't open anything?*");
                }
            }

            if (choice == 0) {
                TextUI.displayMessage();
                String answer = TextUI.getInput("Your guess:").trim();

                if (answer.equalsIgnoreCase("Piano")) {
                    riddleSolved = true;
                } else {
                    TextUI.displayMessage();
                    TextUI.displayMessage("""
                            You say the word out loud but nothing happened.
                            """);

                    tryAgain = true;
                }
            } else if (choice == options.indexOf("Get hint")) {
                penaltyTime += HINT_PENALTY_TIME;

                TextUI.displayMessage();
                TextUI.displayMessage("""
                        Keys? It reminds you of music but you're not quite sure why.
                        """);
                TextUI.getInput("Press Enter to continue...");
                TextUI.displayMessage();

                tryAgain = false;
            }
        }
    }

    @Override
    public void exit() {
        running = false;
        endTime = System.currentTimeMillis();

        if (isComplete) {
            TextUI.displaySuccesMessage("Congratulations!!! You have cleared the first room!");
        }

        TextUI.getInput("Press Enter to continue...");
    }
}