package Player;

import Mapping.DefaultMap;

import java.util.ArrayList;

public class Player {
    private int current_location = 0;
    public int day = 1;
    public ArrayList<ArrayList<String>> loc_visited;
    public ArrayList<String> available_movements, Back_Forward;

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

    private void refresh() {
        Back_Forward.clear();
        Back_Forward.add(loc_visited.get(current_location).get(0));
        if (current_location < loc_visited.size() - 1) {
            Back_Forward.add(loc_visited.get(current_location + 1).get(1));
        } else {
            Back_Forward.add("null");
        }
    }

    public void MoveTo(String nextloc) {
        DefaultMap defaultMap = new DefaultMap();
        if (current_location != loc_visited.size() - 1) {
            if (loc_visited.size() >= current_location + 1) {
                loc_visited.subList(current_location + 1, loc_visited.size() + 1).clear();
            }
            ArrayList<String> this_move = new ArrayList<>();
            this_move.add(loc_visited.get(current_location - 1).get(1));
            this_move.add(nextloc);
            loc_visited.add(this_move);
            current_location = loc_visited.size() - 1;
        } else {
            ArrayList<String> thismove = new ArrayList<>();
            thismove.add(loc_visited.get(current_location - 1).get(1));
            thismove.add(nextloc);
            loc_visited.add(thismove);
            current_location++;
        }
    }

}
