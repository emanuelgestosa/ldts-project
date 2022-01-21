package blackjack.controller.menu;

import blackjack.Game;
import blackjack.controller.Controller;
import blackjack.gui.GUI;
import blackjack.model.game.table.Table;
import blackjack.model.menu.ConfigsMenu;
import blackjack.model.menu.Menu;
import blackjack.states.MenuState;

import java.io.IOException;

public class ConfigsMenuController extends Controller<ConfigsMenu> {
    public ConfigsMenuController(ConfigsMenu model) {
        super(model);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) throws IOException, InterruptedException {
        switch (action) {
            case RIGHT:
                getModel().nextEntry();
                break;
            case LEFT:
                getModel().previousEntry();
                break;
            case SELECT:
                if (getModel().isSelected2()) Table.getInstance().setNDecks(2);
                else if (getModel().isSelected3()) Table.getInstance().setNDecks(3);
                else if (getModel().isSelected4()) Table.getInstance().setNDecks(4);
                else if (getModel().isSelected5()) Table.getInstance().setNDecks(5);
                else if (getModel().isSelected6()) Table.getInstance().setNDecks(6);
                else if (getModel().isSelected7()) Table.getInstance().setNDecks(7);
                else if (getModel().isSelected8()) Table.getInstance().setNDecks(8);
                game.setState(new MenuState(new Menu()));
                break;
            case QUIT:
                game.setState(null);
        }
    }
}
