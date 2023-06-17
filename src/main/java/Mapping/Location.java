package Mapping;

import tech.tablesaw.api.IntColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    public final String name;
    public HashMap<String, Integer> connections;
    public ArrayList<Resident> residents;

    public Location(String name) {
        this.name = name;
        connections = new HashMap<>();
        residents = new ArrayList<>();
    }

    public void add_connection(String LocName, Integer distance) {
        this.connections.put(LocName, distance);
    }

    public Table residentInfo() {
        Table ans = Table.create("Resident").addColumns(
                StringColumn.create("Name"),
                IntColumn.create("Age"),
                StringColumn.create("Gender"),
                StringColumn.create("Residential Area"),
                StringColumn.create("Parents")
        );
        for (Resident resident : residents) {
            ans.append(resident.infoTable);
        }
        return ans;
    }
}
