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
            System.out.println("file not found");
        }
        return data;
    }

    public static void saveScoreData(List<Player> players) {

        try {
            FileWriter writer = new FileWriter("data/scoreData");
            writer.write("Name,Score" + "\n");
            for (Player p : players) {
                String textTosave = p.getName() + "," + p.getTimeScore();
                writer.write(textTosave + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("noget gik galt ved skrivning til fil");
        }
    }
}
