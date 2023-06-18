import Player.Player;
import TUI.Choice;
import TUI.Selection;
import TUI.UI;
import Parser.CsvLoader;
import tech.tablesaw.api.Table;

import java.io.InputStream;
import java.util.ArrayList;

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

        switch (Integer.parseInt(init_screen.get(0))) {
            case 1 -> {
                Selection sel = new Selection();
                ArrayList<String> MapMode = sel.result("Select a map:", new ArrayList<Choice>() {{
                    add(new Choice("Default Map", "map"));
                    add(new Choice("Parallel Map", "map"));
                    add(new Choice("Alternate map", "map"));
                }});
                ui.player.setMap(MapMode.get(2));
            }
            case 2 -> {
                ui.player.LoadFrom(init_screen.get(1));
            }
            case 3 -> {
                return;
            }
        }

        ui.player.townMap.LoadData(residents_table, stands_table);
        int markDay = ui.player.day;
        while (true) {
            int ans = ui.PlayerAction();
            if (ans == 0) {
                break;
            }
            if (ans > markDay) {
                markDay = ans;
                ArrayList<String> days = new ArrayList<>() {{
                    add("Sunday");
                    add("Monday");
                    add("Tuesday");
                    add("Wednesday");
                    add("Thursday");
                    add("Friday");
                    add("Saturday");
                }};
                System.out.println("It's Day " + markDay + " (" + days.get(markDay % 7) + ") of our journey in JOJOLands");
            }
        }
    }
}
