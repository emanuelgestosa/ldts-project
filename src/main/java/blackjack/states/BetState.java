package blackjack.states;

import blackjack.controller.Controller;
import blackjack.controller.game.BetController;
import blackjack.model.game.table.Table;
import blackjack.model.menu.BetMenu;
import blackjack.viewer.Viewer;
import blackjack.viewer.game.BetViewer;

public class BetState extends State<BetMenu> {

    public BetState(BetMenu model) {
        super(model);
    }

    @Override
    protected Viewer<BetMenu> getViewer() {
        return new BetViewer(getModel());
    }

    @Override
    protected Controller<BetMenu> getController() {
        return new BetController(getModel());
    }
}
