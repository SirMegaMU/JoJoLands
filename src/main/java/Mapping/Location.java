package Mapping;

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
}
