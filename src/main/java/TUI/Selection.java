package TUI;

import java.util.ArrayList;
import java.util.Scanner;

public class Selection {
    public Selection() {
    }

    public ArrayList<String> result(String instruction, ArrayList<Choice> choices) {
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
}
