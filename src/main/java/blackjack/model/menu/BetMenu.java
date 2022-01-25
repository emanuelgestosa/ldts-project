package blackjack.model.menu;

import blackjack.model.Page;

import java.util.Arrays;
import java.util.List;

public class BetMenu extends Page {

    public BetMenu()  {
        super(Arrays.asList("Exit", "50", "100", "250", "500", "1000"));
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
}
