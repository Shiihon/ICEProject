import java.util.List;
import java.util.Scanner;

/**
 * A utility class used to communicate with and get input from the user
 */
public final class TextUI {

    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    /**
     * Get text input from the user while printing a small message
     *
     * @param msg The message to show the user (A small space will be added to the end of the message)
     * @return The input from the user in the form of text
     */
    public static String getInput(String msg) {
        System.out.print(msg + " ");
        return scanner.nextLine();
    }

    /**
     * Get a numeric input from the user while printing a small message
     *
     * @param msg  The message to show the user
     * @return The input from the user in the form of an int
     */
    public static int getNumericInput(String msg) {
        String input = getInput(msg);

        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException ignored) {
            displayMessage();
            displayErrorMessage("Please specify a numeric input.");
            return getNumericInput(msg);
        }
    }

    /**
     * Display a list of options to the user and return the index of what option the user chose
     *
     * @param msg     The message to show the user
     * @param options The options displayed to the user
     * @return The index of the choice the user picked in the range of 0 to options.size()-1
     */
    public static int getChoice(String msg, List<?> options) {
        displayOptions(options);
        displayMessage();
        String input = getInput(msg);

        try {
            int choice = Integer.parseInt(input.trim()) - 1;

            if (choice >= 0 && choice < options.size()) {
                return choice;
            }
        } catch (NumberFormatException ignored) {
        }

        displayMessage();
        displayErrorMessage("Please choose a valid option.");
        return getChoice(msg, options);
    }

    /**
     * Ask the user a yes or no question and return their answer
     *
     * @param msg The message to show the user
     * @return The users answer. "Y" for yes and "N" for no
     */
    public static String getChoiceYN(String msg) {
        String input = getInput(msg + " [Y/N]:");

        if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes")) {
            return "Y";
        } else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("No")) {
            return "N";
        } else {
            displayMessage();
            displayErrorMessage("Please choose a valid option.");
            return getChoiceYN(msg);
        }
    }

    /**
     * Display a list of options to the user
     *
     * @param options The list of options that will be displayed to the user
     */
    public static void displayOptions(List<?> options) {
        int count = 1;

        for (Object option : options) {
            displayMessage("[" + count + "] " + option);
            count++;
        }
    }

    /**
     * Display an empty message to the user
     */
    public static void displayMessage() {
        displayMessage("");
    }

    /**
     * Display a message to the user
     *
     * @param msg The message to be displayed to the user
     */
    public static void displayMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Display an error message to the user
     *
     * @param errorMsg The error message to be displayed to the user
     */
    public static void displayErrorMessage(String errorMsg) {
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        System.out.println(RED + errorMsg + RESET);
    }
}
