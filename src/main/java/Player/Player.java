package Player;

import Mapping.TownMap;

import java.util.ArrayList;

public class Player {
    private int current_location = 0;
    public int day = 1;
    public ArrayList<ArrayList<String>> loc_visited;
    public ArrayList<String> available_movements, Back_Forward;
    public String MapName;
    public TownMap townMap;

    public Player() {
        loc_visited = new ArrayList<>();
        available_movements = new ArrayList<>();
        Back_Forward = new ArrayList<>();
        ArrayList<String> init_loc = new ArrayList<>();
        init_loc.add("null");
        init_loc.add("Town Hall");
        loc_visited.add(init_loc);

        refresh();
    }

    public void setMap(String mapName) {
        this.MapName = mapName;
        this.townMap = new TownMap(this.MapName);
        refresh();
    }

    public String getCurrentLocation() {
        return loc_visited.get(current_location).get(1);
    }

    public ArrayList<String> getAvailableDestination() {
        return new ArrayList<>(this.townMap.locations.get(getCurrentLocation()).connections.keySet());
    }

    private void refresh() {
        Back_Forward.clear();
        Back_Forward.add(loc_visited.get(current_location).get(0));
        if (current_location < loc_visited.size() - 1) {
            Back_Forward.add(loc_visited.get(current_location + 1).get(1));
        } else {
            Back_Forward.add("null");
        }
    }

    public void reset() {
        this.loc_visited.clear();
        this.loc_visited.add(new ArrayList<>() {{
            add("null");
            add("Town Hall");
        }});
        refresh();
    }

    public void MoveTo(String nextloc) {
        if (current_location != loc_visited.size() - 1) {
            loc_visited.subList(current_location + 1, loc_visited.size()).clear();
            ArrayList<String> this_move = new ArrayList<>();
            this_move.add(getCurrentLocation());
            this_move.add(nextloc);
            loc_visited.add(this_move);
            current_location = loc_visited.size() - 1;
        } else {
            ArrayList<String> thismove = new ArrayList<>();
            thismove.add(getCurrentLocation());
            thismove.add(nextloc);
            loc_visited.add(thismove);
            current_location++;
        }
        System.out.println(loc_visited);
        refresh();
    }

    public void MoveBnF(String option) {
        switch (option) {
            case "B" -> {
                current_location--;
            }
            case "F" -> {
                current_location++;
            }
        }
        refresh();
    }

    public void NewDay() {
        reset();
        this.day++;
    }

    public void LoadFrom(String FilePath) {

    }

    public void SaveGame(String FilePath) {
    }

}
