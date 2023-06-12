package TUI;

import java.util.HashMap;

public class Multi_Choice extends Choice {
    private final String alphbet;
    private final HashMap<Character, String> ChoiceList;

    public Multi_Choice(String name, String id, String[] choicelist) {
        super(name, id);
        this.alphbet = "ABCDEFG";
        this.ChoiceList = new HashMap<Character, String>();
        for (int i = 0; i < choicelist.length; i++) {
            this.ChoiceList.put(this.alphbet.charAt(i), choicelist[i]);
        }
    }

    @Override
    public String DisplayContent() {
        StringBuilder content = new StringBuilder(this.name);
        content.append(":\n     ");
        for (int i = 0; i < this.ChoiceList.size(); i++) {
            content.append("[").append(this.alphbet.charAt(i)).append("]  ").append(ChoiceList.get(this.alphbet.charAt(i)));
        }
        return content.toString();
    }

    @Override
    public String effect(String option) {
        return this.ChoiceList.get(option.charAt(1));
    }
}
