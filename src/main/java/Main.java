import Player.Player;
import TUI.Choice;
import TUI.UI;
import Parser.CsvLoader;
import tech.tablesaw.api.Table;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        // Load files
        InputStream resident_stream = Main.class.getClassLoader().getResourceAsStream("residents.csv");
        InputStream stand_stream = Main.class.getClassLoader().getResourceAsStream("stands.csv");
        CsvLoader parser = new CsvLoader(resident_stream, stand_stream);
        Table residents_table = parser.load_resident();
        Table stands_table = parser.load_stand();

        UI ui = new UI();
        Player player = new Player();

        ArrayList<String> init_screen = ui.MainScreen();
        switch (init_screen.get(0)) {
            case "1" -> {
                String MapMode = ui.Selection("Select a map:", new ArrayList<Choice>() {{
                    add(new Choice("Default Map"));
                    add(new Choice("Parallel Map"));
                    add(new Choice("Alternate map"));
                }}).get(0);
                player.setMap(MapMode);

            }
            case "2" -> {
                System.out.println(init_screen.get(1));
            }
            case "3" -> {
                return;
            }
        }
        while (true) {
            player = ui.PlayerAction(player);
        }
    }
}
