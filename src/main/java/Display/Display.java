package Display;

import Sort.Sort;
import TUI.Choice;
import TUI.Input_Choice;
import TUI.Selection;
import tech.tablesaw.api.Table;

import java.util.ArrayList;

public class Display {
    public void displayResidentInfo(Table table) {
        System.out.println(table);
        ArrayList<Choice> choices = new ArrayList<>() {{
            add(new Choice("View Resident's Profile", "view"));
            add(new Input_Choice("Sort", "sort", "Enter the sorting order:"));
            add(new Choice("Exit", "exit"));
        }};
        Selection sel = new Selection();
        ArrayList<String> ans = sel.result("", choices);

        //System.out.println(ans);

        switch (ans.get(0)) {
            case "2" -> {
                Sort s = new Sort(table);
                Table t = s.complexSort(ans.get(2));
                System.out.println(t);
                System.out.println("\n");
            }
            case "3" -> {
                return;
            }
        }

    }

}
