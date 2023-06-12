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
    private final Choice save = new Choice("Save Game", "init");
    private final Choice exit = new Choice("Exit", "init");

    private Choice getmove() {
        return new Multi_Choice("Move to:", "Move", player.getAvailableDestination().toArray(new String[0]));
    }

    public UI(Player player) {
        this.player = player;
    }

    public ArrayList<String> MainScreen() {
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Start Game", "init"));
        choices.add(new Input_Choice("Load Game", "init", "Enter the path of your save file:"));
        choices.add(exit);
        return Selection("Welcome, to the fantastical realm of JOJOLands.", choices);
    }

    public boolean PlayerAction() {
        String loc = player.getCurrentLocation();
        String loc_info = "Current Location: " + loc;
        Location currentLocation = player.townMap.locations.get(loc);
        ArrayList<Choice> choices = getAvailablePlayAction(player);
        ArrayList<String> res = Selection(loc_info, choices);

        switch (choices.get(Integer.parseInt(res.get(0))).id) {
            case "exit" -> {
                return !Objects.equals(choices.get(0).id, "exit");
            }
        }
        return true;
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
        choices.add(getmove());
        if (Objects.equals(player.getCurrentLocation(), "Town Hall")) {
            choices.add(new Choice("Advance to Next Day", "nextday"));
        }
        if (!currentLocation.residents.isEmpty()) {
            choices.add(new Choice("View Resident Information", "sp"));
            ArrayList<Stand> stands = new ArrayList<>();
            for (Resident resident : currentLocation.residents) {
                stands.addAll(resident.stands);
            }
            for (Stand stand : stands) {
                choices.add(new Choice(stand.name(), "stand"));
            }
        }
        if (Objects.equals(player.getCurrentLocation(), "Town Hall")) {
            choices.add(save);
            choices.add(exit);
        } else {
            ArrayList<String> bf = player.Back_Forward;
            if (!Objects.equals(bf.get(0), "null")) {
                choices.add(new Choice("Back (" + bf.get(0) + ")", "back"));
            } else if (!Objects.equals(bf.get(1), "null")) {
                choices.add(new Choice("Forward (" + bf.get(1) + ")", "forward"));
            }
            choices.add(new Choice("Back to Town Hall", "reset"));
        }
        return choices;
    }

}
