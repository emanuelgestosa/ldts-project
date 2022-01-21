package blackjack.model.menu;

import java.util.Arrays;
import java.util.List;

public class ConfigsMenu {

    private final List<String> entries;
    private int currentEntry = 0;

    public ConfigsMenu() {
        this.entries = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "Exit");
    }
    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelected2() {
        return isSelected(0);
    }
    public boolean isSelected3() {
        return isSelected(1);
    }
    public boolean isSelected4() {
        return isSelected(2);
    }
    public boolean isSelected5() {
        return isSelected(3);
    }
    public boolean isSelected6() {
        return isSelected(4);
    }
    public boolean isSelected7() {
        return isSelected(5);
    }
    public boolean isSelected8() {
        return isSelected(6);
    }
    public boolean isSelectedExit() { return isSelected(7); }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
