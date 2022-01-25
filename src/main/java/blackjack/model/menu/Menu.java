package blackjack.model.menu;

import blackjack.model.Page;

import java.util.Arrays;
import java.util.List;

public class Menu extends Page {

    public Menu() {
        super(Arrays.asList("Start", "Configs", "Exit"));
    }

        public boolean isSelectedExit() {
            return isSelected(2);
        }

        public boolean isSelectedConfigs() {
            return isSelected(1);
        }

        public boolean isSelectedStart() {
            return isSelected(0);
        }
}
