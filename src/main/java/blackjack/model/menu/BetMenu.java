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

    public int valueSelected(){
        if (isSelected(1))
            return 50;
        else if(isSelected(2))
            return 100;
        else if(isSelected(3))
            return 250;
        else if(isSelected(4))
            return 500;
        else return 1000;
    }
}
