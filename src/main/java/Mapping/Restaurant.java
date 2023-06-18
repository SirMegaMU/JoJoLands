package Mapping;

import tech.tablesaw.api.FloatColumn;
import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.util.ArrayList;

public class Restaurant extends Location {
    public Table menu, waitList, processList;

    public Restaurant(String name) {
        super(name);
        menu = Table.create("Menu").addColumns(
                StringColumn.create("Order"),
                FloatColumn.create("Price")
        );
        waitList = Table.create("Waiting List").addColumns(
                IntColumn.create("No"),
                StringColumn.create("Name"),
                IntColumn.create("Age"),
                StringColumn.create("Gender"),
                StringColumn.create("Order")
        );
        processList = Table.create("Waiting List").addColumns(
                IntColumn.create("No"),
                StringColumn.create("Name"),
                IntColumn.create("Age"),
                StringColumn.create("Gender"),
                StringColumn.create("Order")
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
