package Display;

import Mapping.Location;
import Mapping.Resident;
import Sort.Sort;
import TUI.Choice;
import TUI.Input_Choice;
import TUI.Selection;
import tech.tablesaw.api.Table;

import java.util.ArrayList;

public class Display {
    public void displayResidentInfo(Location loc) {
        Table table = loc.getResidentInfo();
        System.out.println(table);
        ArrayList<Choice> choices = new ArrayList<>() {{
            add(new Input_Choice("View Resident's Profile", "view", "Enter the resident's name:"));
            add(new Input_Choice("Sort", "sort", "Enter the sorting order:"));
            add(new Choice("Exit", "exit"));
        }};
        while (true) {
            Selection sel = new Selection();
            ArrayList<String> ans = sel.result("", choices);
            //System.out.println(ans);
            switch (ans.get(0)) {
                case "1" -> {
                    if (loc.residents.contains(ans.get(2))) {
                        Resident r = loc.residents.get(loc.residents.indexOf(ans.get(2)));
                        System.out.println(r.detialInfo());
                    }
                    continue;
                }
                case "2" -> {
                    Sort s = new Sort(table);
                    Table t = s.complexSort(ans.get(2));
                    System.out.println(t);
                    System.out.println("\n");
                    continue;
                }
                case "3" -> {
                    break;
                }
            }
            break;
        }
    }
}
