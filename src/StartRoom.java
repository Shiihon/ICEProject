

//import jdk.incubator.vector.VectorOperators;

import java.util.ArrayList;
import java.util.List;

public class StartRoom extends ARoom {

    private boolean candleLit = true;
    private boolean bookshelfPuzzleSolved;
    private boolean paintingPuzzleSolved;

    public StartRoom() {
        super();
    }

    @Override
    public void enter() {

    }

    public void runRoomLoop() {

        TextUI.displayMessage("Waszupppp");

        startBookshelfPuzzle();

    }

    private void startBookshelfPuzzle() {
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
                startBookshelfPuzzle();
            }

        }

    }

    private void startPaintingPuzzle() {

    }

    @Override
    public void exit() {

    }
}
