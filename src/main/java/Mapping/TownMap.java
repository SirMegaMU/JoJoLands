package Mapping;

import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.HashMap;

public class TownMap {
    public HashMap<String, Location> locations;
    public final String MapName;
    public final String TownHall = "Town Hall", SanGiorgioMaggiore = "San Giorgio Maggiore",
            JoestarMansion = "Joestar Mansion", TrattoriaTrussardi = "Trattoria Trussardi",
            GreenDophinStreetPrison = "Green Dolphin Street Prison", MoriohGrandHotel = "Morioh Grand Hotel",
            PassioneRestaurant = "Passione Restaurant", SavageGarden = "Savage Garden", Vineyard = "Vineyard",
            CafeDeuxMagots = "Cafe Deux Magots", DIOsMansion = "DIO's Mansion", AngeloRock = "Angelo Rock",
            PolnareffLand = "Polnareff Land", JadeGarden = "Jade Garden", Libeccio = "Libeccio";

    public TownMap(String mapName) {
        this.locations = new HashMap<>();
        MapName = mapName;
        add_locations();
        switch (MapName) {
            case "Default Map" -> setDefaultMap();
            case "Parallel Map" -> setParallelMap();
            case "Alternate map" -> setAlternateMap();
        }
    }

    public void LoadData(Table resident_table, Table stand_table) {
        HashMap<String, Resident> residents = new HashMap<>();
        for (Row row : resident_table) {
            residents.put(row.getString(0),
                    new Resident(row.getString(0),
                            row.getInt(1),
                            row.getString(2),
                            row.getString(3),
                            row.getString(4)
                    )
            );
        }
        for (Row row : stand_table) {
            residents.get(row.getString(1)).stands.add(
                    new Stand(
                            row.getString(0), row.getString(1),
                            row.getString(2), row.getString(3),
                            row.getString(4), row.getString(5),
                            row.getString(6), row.getString(7)
                    )
            );
        }
        for (Resident resident : residents.values()) {
            locations.get(resident.residentialArea).residents.add(resident);
        }
    }

    private void add_connection(String loc1, String loc2, int distance) {
        this.locations.get(loc1).add_connection(loc2, distance);
        this.locations.get(loc2).add_connection(loc1, distance);
    }

    private void add_locations() {
        this.locations.put(TownHall, new Location(TownHall));
        this.locations.put(MoriohGrandHotel, new Location(MoriohGrandHotel));
        this.locations.put(TrattoriaTrussardi, new Location(TrattoriaTrussardi));
        this.locations.put(GreenDophinStreetPrison, new Location(GreenDophinStreetPrison));
        this.locations.put(AngeloRock, new Location(AngeloRock));
        this.locations.put(PolnareffLand, new Location(PolnareffLand));
        this.locations.put(CafeDeuxMagots, new Location(CafeDeuxMagots));
        this.locations.put(JadeGarden, new Location(JadeGarden));
        this.locations.put(SanGiorgioMaggiore, new Location(SanGiorgioMaggiore));
        this.locations.put(Libeccio, new Location(Libeccio));
        this.locations.put(DIOsMansion, new Location(DIOsMansion));
        this.locations.put(SavageGarden, new Location(SavageGarden));
        this.locations.put(JoestarMansion, new Location(JoestarMansion));
        this.locations.put(Vineyard, new Location(Vineyard));
        this.locations.put(PassioneRestaurant, new Location(PassioneRestaurant));
    }

    private void setDefaultMap() {
        add_connection(TownHall, CafeDeuxMagots, 4);
        add_connection(TownHall, JadeGarden, 5);
        add_connection(TownHall, MoriohGrandHotel, 5);
        add_connection(MoriohGrandHotel, JadeGarden, 3);
        add_connection(MoriohGrandHotel, TrattoriaTrussardi, 6);
        add_connection(TrattoriaTrussardi, SanGiorgioMaggiore, 3);
        add_connection(TrattoriaTrussardi, GreenDophinStreetPrison, 6);
        add_connection(GreenDophinStreetPrison, Libeccio, 3);
        add_connection(GreenDophinStreetPrison, AngeloRock, 2);
        add_connection(AngeloRock, DIOsMansion, 3);
        add_connection(PolnareffLand, CafeDeuxMagots, 4);
        add_connection(PolnareffLand, SavageGarden, 6);
        add_connection(CafeDeuxMagots, SavageGarden, 4);
        add_connection(CafeDeuxMagots, JadeGarden, 3);
        add_connection(JadeGarden, JoestarMansion, 2);
        add_connection(JadeGarden, SanGiorgioMaggiore, 2);
        add_connection(SanGiorgioMaggiore, Libeccio, 4);
        add_connection(Libeccio, JoestarMansion, 6);
        add_connection(Libeccio, Vineyard, 6);
        add_connection(Libeccio, DIOsMansion, 2);
        add_connection(DIOsMansion, Vineyard, 3);
        add_connection(SavageGarden, JoestarMansion, 4);
        add_connection(SavageGarden, Vineyard, 8);
        add_connection(JoestarMansion, Vineyard, 3);
    }

    private void setParallelMap() {
        add_connection(SanGiorgioMaggiore, JoestarMansion, 5);
        add_connection(SanGiorgioMaggiore, SavageGarden, 6);
        add_connection(JoestarMansion, JadeGarden, 3);
        add_connection(JoestarMansion, MoriohGrandHotel, 4);
        add_connection(JoestarMansion, TrattoriaTrussardi, 5);
        add_connection(TrattoriaTrussardi, TownHall, 6);
        add_connection(TrattoriaTrussardi, AngeloRock, 3);
        add_connection(TrattoriaTrussardi, DIOsMansion, 4);
        add_connection(AngeloRock, DIOsMansion, 1);
        add_connection(AngeloRock, GreenDophinStreetPrison, 8);
        add_connection(DIOsMansion, GreenDophinStreetPrison, 6);
        add_connection(JadeGarden, SavageGarden, 4);
        add_connection(JadeGarden, CafeDeuxMagots, 3);
        add_connection(MoriohGrandHotel, CafeDeuxMagots, 6);
        add_connection(SavageGarden, CafeDeuxMagots, 5);
        add_connection(CafeDeuxMagots, TownHall, 4);
        add_connection(CafeDeuxMagots, PolnareffLand, 2);
        add_connection(TownHall, Libeccio, 2);
        add_connection(TownHall, Vineyard, 3);
        add_connection(Libeccio, Vineyard, 3);
    }

    private void setAlternateMap() {
        add_connection(SavageGarden, SavageGarden, 6);
        add_connection(SanGiorgioMaggiore, MoriohGrandHotel, 6);
        add_connection(MoriohGrandHotel, JoestarMansion, 4);
        add_connection(MoriohGrandHotel, GreenDophinStreetPrison, 2);
        add_connection(MoriohGrandHotel, TownHall, 2);
        add_connection(JoestarMansion, TrattoriaTrussardi, 5);
        add_connection(PassioneRestaurant, GreenDophinStreetPrison, 4);
        add_connection(PassioneRestaurant, PassioneRestaurant, 1);
        add_connection(PassioneRestaurant, TownHall, 7);
        add_connection(PassioneRestaurant, CafeDeuxMagots, 4);
        add_connection(PassioneRestaurant, AngeloRock, 6);
        add_connection(PassioneRestaurant, DIOsMansion, 2);
        add_connection(PolnareffLand, AngeloRock, 2);
        add_connection(PolnareffLand, DIOsMansion, 2);
        add_connection(PolnareffLand, JadeGarden, 2);
        add_connection(AngeloRock, JadeGarden, 1);
        add_connection(TownHall, GreenDophinStreetPrison, 3);
        add_connection(SavageGarden, Vineyard, 4);
        add_connection(Vineyard, CafeDeuxMagots, 4);
        add_connection(CafeDeuxMagots, DIOsMansion, 1);
    }
}
