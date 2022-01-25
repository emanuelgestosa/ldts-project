package blackjack.model.menu;

import blackjack.model.Page;

import java.util.Arrays;
import java.util.List;

public class ConfigsMenu extends Page {

    public ConfigsMenu() {
        super(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "Exit"));
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
}
