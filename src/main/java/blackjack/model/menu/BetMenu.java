package blackjack.model.menu;

import java.util.Arrays;
import java.util.List;

public class BetMenu {
    private final List<String> entries;
    private int currentEntry = 0;

    public BetMenu()  {
        this.entries = Arrays.asList("Exit", "50", "100", "250", "500", "1000");
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

    public boolean isSelectedExit() {
        return isSelected(0);
    }

    public boolean isSelected50() {
        return isSelected(1);
    }

    public boolean isSelected100() {
        return isSelected(2);
    }
    public boolean isSelected250() {
        return isSelected(3);
    }
    public boolean isSelected500() {
        return isSelected(4);
    }
    public boolean isSelected1000() {
        return isSelected(5);
    }

    public int getNumberEntries() {
        return this.entries.size();
    }
}
