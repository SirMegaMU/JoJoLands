package Mapping;

import java.util.HashMap;

public class DefaultMap {
    public HashMap<String, Location> locations;

    public DefaultMap() {
        this.locations.put("Town Hall", new Location("TownHall"));
        this.locations.put("Morioh Grand Hotel", new Location("Morioh Grand Hotel"));
        this.locations.put("Trattoria Trussardi", new Location("Trattoria Trussardi"));
        this.locations.put("Green Dophin Street Prison", new Location("Green Dophin Street Prison"));
        this.locations.put("Angelo Rock", new Location("Angelo Rock"));
        this.locations.put("Polnareff Land", new Location("Polnareff Land"));
        this.locations.put("Cafe Deux Magots", new Location("Cafe Deux Magots"));
        this.locations.put("Jade Garden", new Location("Jade Garden"));
        this.locations.put("San Giorgio Maggiore", new Location("San Giorgio Maggiore"));
        this.locations.put("Libeccio", new Location("Libeccio"));
        this.locations.put("DIO's Mansion", new Location("DIO's Mansion"));
        this.locations.put("Savage Garden", new Location("Savage Garden"));
        this.locations.put("Joestar Mansion", new Location("Joestar Mansion"));
        this.locations.put("Vineyard", new Location("Vineyard"));

        add_connections("Town Hall", "Cafe Deux magots", 4);
        add_connections("Town Hall", "Jade Garden", 5);
        add_connections("Town Hall", "Morioh Grand Hotel", 5);
        add_connections("Morioh Grand Hotel", "Jade Garden", 3);
        add_connections("Morioh Grand Hotel", "Trattoria Trussardi", 6);
        add_connections("Trattoria Trussardi", "San Giorgio Maggiore", 3);
        add_connections("Trattoria Trussardi", "Green Dophin Street Prison", 6);
        add_connections("Green Dophin Street Prison", "Libeccio", 3);
        add_connections("Green Dophin Street Prison", "Angelo Rock", 2);
        add_connections("Angelo Rock", "DIO's Mansion", 3);
        add_connections("Polnareff Land", "Cafe Deux Magots", 4);
        add_connections("Polnareff Land", "Savage Garden", 6);
        add_connections("Cafe Deux Magots", "Savage Garden", 4);
        add_connections("Cafe Deux Magots", "Jade Garden", 3);
        add_connections("Jade Garden", "Joestar Mansion", 2);
        add_connections("Jade Garden", "San Giorgio Maggiore", 2);
        add_connections("San Giorgio Maggiore", "Libeccio", 4);
        add_connections("Libeccio", "Joestar Mansion", 6);
        add_connections("Libeccio", "Vineyard", 6);
        add_connections("Libeccio", "DIO's Mansion", 2);
        add_connections("DIO's Mansion", "Vineyard", 3);
        add_connections("Savage Garden", "Joestar Mansion", 4);
        add_connections("Savage Garden", "Vineyard", 8);
        add_connections("Joestar Mansion", "Vineyard", 3);
    }

    private void add_connections(String loc1, String loc2, int distance) {
        this.locations.get(loc1).add_connection(loc2, distance);
        this.locations.get(loc2).add_connection(loc1, distance);
    }


}
