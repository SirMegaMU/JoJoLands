package TUI;

import Display.Display;
import Mapping.Location;
import Mapping.Resident;
import Mapping.Stand;
import Player.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class UI {
    public Player player;
    private final Choice start = new Choice("Start Game", "init");
    private final Choice save = new Input_Choice("Save Game", "save", "Enter the path of your save file:");
    private final Choice load = new Input_Choice("Load Game", "init", "Enter the path of your save file:");
    private final Choice exit = new Choice("Exit", "init");
    private final Choice nextday = new Choice("Advance to Next Day", "nextday");
    private final Choice resinfo = new Choice("View Resident Information", "res_info");
    private final Choice backtt = new Choice("Back to Town Hall", "reset");
    private final Choice listOres = new Choice("View Waiting List and Order Processing List", "listOres");

    private Multi_Choice get_stand() {
        String loc = player.getCurrentLocation();
        Location currentLocation = player.townMap.locations.get(loc);
        ArrayList<Stand> stands = new ArrayList<>();
        ArrayList<String> stands_string = new ArrayList<>();
        for (Resident resident : currentLocation.residents) {
            stands.addAll(resident.stands);
        }
        for (Stand stand : stands) {
            stands_string.add(stand.name());
        }
        return new Multi_Choice("Stands", "stand", stands_string.toArray(new String[0]));
    }

    private Choice get_move() {
        return new Multi_Choice("Move to", "move", player.getAvailableDestination().toArray(new String[0]));
    }

    private ArrayList<Choice> get_bnf() {
        ArrayList<Choice> choices = new ArrayList<>();
        ArrayList<String> bf = player.Back_Forward;
        if (!Objects.equals(bf.get(0), "null")) {
            choices.add(new Choice("Back (" + bf.get(0) + ")", "back"));
        }
        if (!Objects.equals(bf.get(1), "null")) {
            choices.add(new Choice("Forward (" + bf.get(1) + ")", "forward"));
        }
        return choices;
    }

    public UI(Player player) {
        this.player = player;
    }

    public ArrayList<String> MainScreen() {
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(start);
        choices.add(load);
        choices.add(exit);
        Selection sel = new Selection();
        return sel.result("Welcome, to the fantastical realm of JOJOLands.", choices);
    }

    public int PlayerAction() throws IOException {
        String loc = player.getCurrentLocation();
        String loc_info = "Current Location: " + loc;
        Location currentLocation = player.townMap.locations.get(loc);
        ArrayList<Choice> choices = getAvailablePlayAction(player);
        Selection sel = new Selection();
        ArrayList<String> res = sel.result(loc_info, choices);

        switch (choices.get(Integer.parseInt(res.get(0)) - 1).id) {

            case "back" -> player.MoveBnF("B");
            case "forward" -> player.MoveBnF("F");
            case "reset" -> player.reset();
            case "nextday" -> player.NewDay();
            case "save" -> player.SaveGame(res.get(2));
            case "move" -> player.MoveTo(res.get(2));
            case "res_info" -> new Display() {{
                displayResidentInfo(currentLocation);
            }};
            case "listOres" -> {
                ;
            }
            case "stand" -> {
                ;
            }
            case "exit" -> {
                return 0;
            }
        }
        return player.day;
    }


    public ArrayList<Choice> getAvailablePlayAction(Player player) {
        String loc = player.getCurrentLocation();
        Location currentLocation = player.townMap.locations.get(loc);
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(get_move());
        if (Objects.equals(currentLocation.info, "restaurant")) {
            choices.add(listOres);
        }
        if (Objects.equals(player.getCurrentLocation(), "Town Hall")) {
            choices.add(nextday);
        }
        if (!currentLocation.residents.isEmpty()) {
            choices.add(resinfo);
            choices.add(get_stand());
        }
        choices.addAll(get_bnf());
        choices.add(backtt);
        if (Objects.equals(player.getCurrentLocation(), "Town Hall")) {
            choices.add(save);
            choices.add(exit);
        }
        return choices;
    }
}
