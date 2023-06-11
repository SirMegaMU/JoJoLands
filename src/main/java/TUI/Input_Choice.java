package TUI;

import java.util.Scanner;

public class Input_Choice extends Choice {
    private final String InputInstruction;

    public Input_Choice(String name, String instruction) {
        super(name);
        this.InputInstruction = instruction;
    }

    @Override
    public String effect(String option) {
        System.out.print(this.InputInstruction);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
