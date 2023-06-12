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

        UI ui = new UI(new Player());

        ArrayList<String> init_screen = ui.MainScreen();
        System.out.println(init_screen);
        switch (Integer.parseInt(init_screen.get(0))) {
            case 1 -> {
                String MapMode = ui.Selection("Select a map:", new ArrayList<Choice>() {{
                    add(new Choice("Default Map", "map"));
                    add(new Choice("Parallel Map", "map"));
                    add(new Choice("Alternate map", "map"));
                }}).get(0);
                ui.player.setMap(MapMode);
            }
            case 2 -> {
                ui.player.LoadFrom(init_screen.get(1));
            }
            case 3 -> {
                return;
            }
        }
        // while (true) {boolean res = ui.PlayerAction();if (!res) {break;}}
    }
}
