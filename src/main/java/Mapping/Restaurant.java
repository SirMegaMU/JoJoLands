package Mapping;

import tech.tablesaw.api.FloatColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.util.ArrayList;

public class Restaurant extends Location {
    public Table menu;

    public Restaurant(String name) {
        super(name);
        menu = Table.create("Menu").addColumns(
                StringColumn.create("Order"),
                FloatColumn.create("Price")
        );
        this.info = "restaurant";
    }

    public void addMenu(ArrayList<String> name, ArrayList<Float> price) {
        StringColumn n = StringColumn.create("Order");
        FloatColumn p = FloatColumn.create("Price");
        if (name.size() == price.size()) {
            for (int i = 0; i < name.size(); i++) {
                n.append(name.get(i));
                p.append(price.get(i));
            }
        }
        menu.addRow(0, Table.create().addColumns(n, p));
    }
}
