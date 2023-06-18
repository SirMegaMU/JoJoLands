package Sort;

import tech.tablesaw.api.Table;

import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sort {
    private final HashMap<String, Integer> order = new HashMap<>() {{
        put("Infinity", 0);
        put("A", 1);
        put("B", 2);
        put("C", 3);
        put("D", 4);
        put("E", 5);
        put("?", 6);
        put("Null", 7);
    }};
    private Table table;

    public Sort(Table table) {
        this.table = table;
    }

    public Table complexSort(String orders) {
        String REGEX = "([^\\(\\)]*?)\\b \\((ASC|DESC)\\);";
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(orders);
        while (m.find()) {
            String colname = m.group(1);
            String direc = m.group(2);
            switch (colname) {
                case "No", "Name", "Age", "Gender", "Stand" -> {
                    this.table = sort(colname, Objects.equals(direc, "ASC"));
                }
                case "Destructive Power", "Speed", "Range", "Stamina", "Precision", "Development Potential" -> {
                    this.table = sort_sp(colname, Objects.equals(direc, "ASC"));
                }
            }
        }

        return this.table;
    }

    public Table sort(String name, boolean asc) {
        if (asc) {
            this.table = this.table.sortAscendingOn(name);
        } else {
            this.table = this.table.sortDescendingOn(name);
        }
        return table;
    }

    public Table sort_sp(String name, boolean asc) {
        Table t = quickSort(name, 0, table.rowCount() - 1);
        if (!asc) {
            t.clear();
            for (int i = 0; i < this.table.rowCount(); i++) {
                t.addRow(0, this.table.dropRange(0, 1));
            }
            this.table = t;
        }
        return this.table;
    }

    private Table quickSort(String name, int left, int right) {
        if (order.get(this.table.column(name).get(0)) < order.get(this.table.column(name).get(this.table.rowCount() - 1))) {
            int partitionIndex = partition(this.table, name, order.get(this.table.column(name).get(0)), order.get(this.table.column(name).get(this.table.rowCount() - 1)));
            this.table = quickSort(name, left, partitionIndex - 1);
            this.table = quickSort(name, partitionIndex + 1, right);
        }
        return this.table;
    }

    private int partition(Table t, String name, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (order.get(t.column(name).get(i)) < order.get(t.column(name).get(pivot))) {
                t = swap(t, i, index);
                index++;
            }
        }
        t = swap(t, pivot, index - 1);
        this.table = t;
        return index - 1;

    }

    private Table swap(Table t, int i, int j) {
        Table temp = t.dropRange(j);
        t.dropRange(i);
        t.addRow(i, t.dropRange(j));
        t.addRow(j, temp);
        return t;
    }
}
