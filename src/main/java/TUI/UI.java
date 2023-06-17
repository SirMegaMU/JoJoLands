package TUI;

import Mapping.Location;
import Mapping.Resident;
import Mapping.Stand;
import Player.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UI {
    public Player player;
    private final Choice start = new Choice("Start Game", "init");
    private final Choice save = new Input_Choice("Save Game", "save", "Enter the path of your save file:");
    private final Choice load = new Input_Choice("Load Game", "init", "Enter the path of your save file:");
    private final Choice exit = new Choice("Exit", "init");
    private final Choice nextday = new Choice("Advance to Next Day", "nextday");
    private final Choice resinfo = new Choice("View Resident Information", "res_info");
    private final Choice backtt = new Choice("Back to Town Hall", "reset");

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
        Multi_Choice stand_choices = new Multi_Choice("Stands", "stand", stands_string.toArray(new String[0]));
        return stand_choices;
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
        return Selection("Welcome, to the fantastical realm of JOJOLands.", choices);
    }

    public int PlayerAction() {
        String loc = player.getCurrentLocation();
        String loc_info = "Current Location: " + loc;
        Location currentLocation = player.townMap.locations.get(loc);
        ArrayList<Choice> choices = getAvailablePlayAction(player);
        ArrayList<String> res = Selection(loc_info, choices);

        switch (choices.get(Integer.parseInt(res.get(0)) - 1).id) {

            case "back" -> player.MoveBnF("B");
            case "forward" -> player.MoveBnF("F");
            case "reset" -> player.reset();
            case "nextday" -> player.NewDay();
            case "save" -> player.SaveGame(res.get(2));
            case "move" -> player.MoveTo(res.get(2));
            case "res_info" -> System.out.println(currentLocation.residentInfo());
            case "stand" -> {
                ;
            }
            case "exit" -> {
                return 0;
            }
        }
        return player.day;
    }

    public ArrayList<String> Selection(String instruction, ArrayList<Choice> choices) {
        System.out.println(instruction);
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < choices.size(); i++) {
            System.out.println("[" + (i + 1) + "]  " + choices.get(i).DisplayContent());
        }
        System.out.print("\nSelect:");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ans.add(input);
        int i = Integer.parseInt(input.substring(0, 1));
        if (i < choices.size()) {
            ans.add(choices.get(i - 1).effect(input));
        }
        System.out.println("============================");
        ans.add(0, Character.getNumericValue(ans.get(0).charAt(0)) + "");
        return ans;
    }

    public ArrayList<Choice> getAvailablePlayAction(Player player) {
        String loc = player.getCurrentLocation();
        Location currentLocation = player.townMap.locations.get(loc);
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(get_move());
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
