package blackjack.model;

import java.util.List;

public abstract class Page {
    protected final List<String> entries;
    protected int currentEntry = 0;

    protected Page(List<String> entries){
        this.entries = entries;
    }

    public void previousEntry() {
        currentEntry--;
        if (currentEntry < 0)
            currentEntry = this.entries.size() - 1;
    }

    public void nextEntry() {
        currentEntry++;
        if (currentEntry > this.entries.size() - 1)
            currentEntry = 0;
    }

    public int getNumberEntries() {
        return this.entries.size();
    }

    public String getEntry(int i) {
        return entries.get(i);
    }

    public boolean isSelected(int i) { return currentEntry == i; }
}
