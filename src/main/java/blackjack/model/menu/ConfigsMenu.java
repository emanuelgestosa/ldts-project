package blackjack.model.menu;

import blackjack.model.Page;

import java.util.Arrays;
import java.util.List;

public class ConfigsMenu extends Page {

    public ConfigsMenu() {
        super(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "Exit"));
    }

    public boolean isSelectedExit() { return isSelected(7); }

    public int valueSelected(){
        if (isSelected(0))
            return 2;
        else if(isSelected(1))
            return 3;
        else if(isSelected(2))
            return 4;
        else if(isSelected(3))
            return 5;
        else if(isSelected(4))
            return 6;
        else if(isSelected(5))
            return 7;
        else return 8;
    }
}
