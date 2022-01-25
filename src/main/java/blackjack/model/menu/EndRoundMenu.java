package blackjack.model.menu;

import blackjack.model.Page;

import java.util.Arrays;
import java.util.List;

public class EndRoundMenu extends Page {

    public EndRoundMenu() { super(Arrays.asList("New Round", "Exit")); }

    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public boolean isSelectedNewRound() {
        return isSelected(0);
    }
}
