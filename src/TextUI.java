import java.util.List;
import java.util.Scanner;

public class TextUI {

    private final Scanner scanner;

    public TextUI() {
        scanner = new Scanner(System.in);
    }

    public String getInput(String msg) {
        return null;
    }

    public int getChoice(String msg, List<?> options) {
        return -1;
    }

    public String getChoiceYN(String msg) {
        return null;
    }

    public void displayOptions(List<?> options) {

    }

    public void displayMessage(String msg) {

    }

    public void displayErrorMessage(String errorMsg) {

    }
}
