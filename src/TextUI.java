import java.util.List;
import java.util.Scanner;

public final class TextUI {

    private static final Scanner scanner;

    static {
        scanner = new Scanner(System.in);
    }

    public static String getInput(String msg) {
        System.out.print(msg + " ");
        return scanner.nextLine();
    }

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

        displayErrorMessage("Please choose a valid option.");
        return getChoice(msg, options);
    }

    public static String getChoiceYN(String msg) {
        String input = getInput(msg + " [Y/N]:");

        if (input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("Yes")) {
            return "Y";
        } else if (input.equalsIgnoreCase("N") || input.equalsIgnoreCase("No")) {
            return "N";
        } else {
            displayErrorMessage("Please choose a valid option.");
            return getChoiceYN(msg);
        }
    }

    public static void displayOptions(List<?> options) {
        int count = 1;

        for (Object option : options) {
            displayMessage("[" + count + "] " + option);
            count++;
        }
    }

    public static void displayMessage() {
        displayMessage("");
    }

    public static void displayMessage(String msg) {
        System.out.println(msg);
    }

    public static void displayErrorMessage(String errorMsg) {
        String RED = "\u001B[31m";
        String RESET = "\u001B[0m";
        System.out.println(RED + errorMsg + RESET);
    }
}
