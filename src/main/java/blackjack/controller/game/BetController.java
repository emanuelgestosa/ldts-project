package blackjack.controller.game;

import blackjack.Game;
import blackjack.controller.Controller;
import blackjack.gui.GUI;
import blackjack.model.game.table.Table;
import blackjack.model.menu.BetMenu;
import blackjack.model.menu.Menu;
import blackjack.states.GameState;
import blackjack.states.MenuState;

public class BetController extends Controller<BetMenu> {

    public BetController(BetMenu betmenu) {
        super(betmenu);
    }

    @Override
    public void step(Game game, GUI.ACTION action, long time) {
        if (Table.getInstance().getPlayer().getBalance() < 50) {
            game.setState(new MenuState(new Menu()));
            return;
        }
        switch (action) {
            case RIGHT:
                getModel().nextEntry();
                break;
            case LEFT:
                getModel().previousEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(new MenuState(new Menu()));
                else if (getModel().isSelected50())  {
                    if (Table.getInstance().getPlayer().getBalance() < 50) break;
                    Table.getInstance().dealCards();
                    Table.getInstance().getPlayer().getHand().setBet(50);
                    game.setState(new GameState(Table.getInstance()));
                }
                else if (getModel().isSelected100()) {
                    if (Table.getInstance().getPlayer().getBalance() < 100) break;
                    Table.getInstance().dealCards();
                    Table.getInstance().getPlayer().getHand().setBet(100);
                    game.setState(new GameState(Table.getInstance()));
                }
                else if (getModel().isSelected250()) {
                    if (Table.getInstance().getPlayer().getBalance() < 250) break;
                    Table.getInstance().dealCards();
                    Table.getInstance().getPlayer().getHand().setBet(250);
                    game.setState(new GameState(Table.getInstance()));
                }
                else if (getModel().isSelected500()) {
                    if (Table.getInstance().getPlayer().getBalance() < 500) break;
                    Table.getInstance().dealCards();
                    Table.getInstance().getPlayer().getHand().setBet(500);
                    game.setState(new GameState(Table.getInstance()));
                }
                else if (getModel().isSelected1000()) {
                    if (Table.getInstance().getPlayer().getBalance() < 1000) break;
                    Table.getInstance().dealCards();
                    Table.getInstance().getPlayer().getHand().setBet(1000);
                    game.setState(new GameState(Table.getInstance()));
                }
                break;
            case QUIT:
                game.setState(null);
        }

    }
}
