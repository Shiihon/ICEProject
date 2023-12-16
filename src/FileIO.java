import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class FileIO {

    public static List<String> readScoreData(String path) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(path);

        try {
            Scanner scan = new Scanner(file);
            scan.nextLine(); //Skip header

            while (scan.hasNextLine()) {
                String s = scan.nextLine();
                data.add(s);
            }
        } catch (FileNotFoundException e) {
            TextUI.displayErrorMessage("Error: File " + file.getParentFile().getName() + "/" + file.getName() + " not found.");
        }
        return data;
    }

    public static void saveScoreData(String path, List<Player> players) {
        File file = new File(path);

        if (!ensureFileExistence(file)) {
            TextUI.displayErrorMessage("Error: Could not ensure the existence of file " + file.getParentFile().getName() + "/" + file.getName() + ".");
            return;
        }

        try {
            FileWriter writer = new FileWriter(file);
            writer.write("Name,Score\n");

            for (Player p : players) {
                String textTosave = p.getName() + "," + p.getTimeScore();
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            TextUI.displayErrorMessage("Error: Something went wrong while writing user data to file " + file.getParentFile().getName() + "/" + file.getName() + ".");
        }
    }

    private static boolean ensureFileExistence(File file) {
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
            return false;
        }

        try {
            file.createNewFile();
            return true;
        } catch (IOException e) {
            TextUI.displayErrorMessage("Error: Could not create file " + file.getParentFile().getName() + "/" + file.getName());
            return false;
        }
    }
}
