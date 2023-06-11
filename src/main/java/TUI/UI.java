package TUI;

import Mapping.Location;
import Mapping.Resident;
import Mapping.Stand;
import Player.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UI {
    public UI() {
    }

    public ArrayList<String> MainScreen() {
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice("Start Game"));
        choices.add(new Input_Choice("Load Game", "Enter the path of your save file:"));
        choices.add(new Choice("Exit"));
        return Selection("Welcome, to the fantastical realm of JOJOLands.", choices);
    }

    public Player PlayerAction(Player player) {
        String loc = player.getCurrentLocation();
        String loc_info = "Current Location: " + loc;
        Location currentLocation = player.townMap.locations.get(loc);
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Multi_Choice("Move to:", player.getAvailableDestination().toArray(new String[0])));
        if (Objects.equals(player.getCurrentLocation(), "Town Hall")) {
            choices.add(new Choice("Advance to Next Day"));
        }
        if (!currentLocation.residents.isEmpty()) {
            choices.add(new Choice("View Resident Information"));
            ArrayList<Stand> stands = new ArrayList<>();
            for (Resident resident : currentLocation.residents) {
                stands.addAll(resident.stands);
            }
            for (Stand stand : stands) {
                choices.add(new Choice(stand.name()));
            }
        }
        if (Objects.equals(player.getCurrentLocation(), "Town Hall")) {
            choices.add(new Choice("Save Game"));
            choices.add(new Choice("Exit"));
        } else {
            ArrayList<String> bf = player.Back_Forward;
            if (!Objects.equals(bf.get(0), "null")) {
                choices.add(new Choice("Back (" + bf.get(0) + ")"));
            } else if (!Objects.equals(bf.get(1), "null")) {
                choices.add(new Choice("Forward (" + bf.get(1) + ")"));
            }
            choices.add(new Choice("Back to Town Hall"));
        }
        ArrayList<String> res = Selection(loc_info, choices);
        return player;
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
        return ans;
    }

}
