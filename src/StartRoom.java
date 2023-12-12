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
        TextUI.displayMessage("You begin walking to the big bookshelf");

        if(!candleLit)
        {
            TextUI.displayMessage("you see a lot of books on the shelf but you can't read and not even make out any of the titles.");

        }

        if(candleLit)
        {
            TextUI.displayMessage(" You see a lot of books: “IT, Saw, American Psycho, Sinister”. At the top of the shelf there is a sign “Horror”. You quickly realize that all the books are old and new Disney movies and you ask yourself “Why”?\n" +
                    "Some of the books are very old and others new. There are some that catch your eye more than others. The Exorcist, Smile, The nun and Scream . \n");

            List<String> options = new ArrayList<>();

            options.add("The Exorcsit");
            options.add("Scream");
            options.add("The nun");
            options.add("Smile");
            options.add("Look around");

            int choice = TextUI.getChoice("What do you want to do?",options);

            if(choice == options.indexOf("Smile"))
            {
                TextUI.displayMessage(" You take "+ choice +" out the bookshelf. After 3 sek, you hear a loud noise, the bookshelf starts moving. Another 10 sec goes by, the bookshelf has now moved 1meter to the left revealing a keypad");
                List<String>Key = new ArrayList<>();
                Key.add("NumberPad");
                Key.add("Look around");
                int input = TextUI.getChoice("What do you want to do", Key);

                if(input == Key.indexOf("NumberPad"))
                {
                    TextUI.displayMessage("You take a step closer to the number pad, there is number form 1-9 and a green button on it, with the word “confirm” on it. You see there are some options, but  don’t really know what to do or what’s gonna happen. Or what will be if you press the wrong numbers, so you decide not to do anything right now and look around again.");
                    runRoomLoop();
                } else
                {
                    TextUI.displayMessage("You take a Step back start looking around agian");
                    runRoomLoop();
                }

            } else if (choice == options.indexOf("Look around"))
            {
                runRoomLoop();
            } else {
                TextUI.displayMessage("you take " + choice + " the book is full of dust. you start going through the pages reading the story, but nothing happens. You put the book back on the shelf, take a step back, you're looking at the bookshelf again.");
                approachBookshelf();
            }
        }
    }

    private void approachPainting() {

    }

    @Override
    public void exit() {
        System.out.println("Exit room");
    }
}
