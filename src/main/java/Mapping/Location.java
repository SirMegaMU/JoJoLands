package Mapping;

import java.util.ArrayList;
import java.util.HashMap;

public class Location {
    public final String name;
    public ArrayList<HashMap<String, Integer>> connections;

    public Location(String name) {
        this.name = name;
    }

    public void add_connection(String LocName, Integer distance) {
        HashMap<String, Integer> connection = new HashMap<>();
        connection.put(LocName, distance);
        connections.add(connection);
    }
}
