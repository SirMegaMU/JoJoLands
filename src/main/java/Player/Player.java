package Player;

import Mapping.TownMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
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
        this.current_location = 0;
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
        //System.out.println(loc_visited);
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

    public Player LoadFrom(String FilePath) throws IOException {
        File f = new File(FilePath);
        InputStream in = new FileInputStream(f);
        byte[] data = new byte[(int) f.length()];
        int len = in.read(data);
        String str = new String(data, 0, len);
        ObjectMapper objectMapper = new ObjectMapper();
        Player player = objectMapper.readValue(str, Player.class);
        return player;
    }

    public void SaveGame(String FilePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String playerJson = objectMapper.writeValueAsString(this);
        OutputStream fos = new FileOutputStream(FilePath);
        fos.write(playerJson.getBytes());
        fos.close();
    }

}
