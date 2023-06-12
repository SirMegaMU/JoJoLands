package TUI;

public class Choice {
    final String name;
    public final String id;

    public Choice(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String DisplayContent() {
        return this.name;
    }

    public String effect(String option) {
        return this.name;
    }
}
