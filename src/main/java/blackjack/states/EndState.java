package blackjack.states;

import blackjack.controller.Controller;
import blackjack.controller.game.EndStateController;
import blackjack.model.game.table.Table;
import blackjack.model.menu.EndRoundMenu;
import blackjack.viewer.Viewer;
import blackjack.viewer.game.EndStateViewer;

public class EndState extends State<EndRoundMenu> {
    public EndState(EndRoundMenu model) {
        super(model);
    }

    @Override
    protected Viewer<EndRoundMenu> getViewer() {
        return new EndStateViewer(getModel());
    }

    @Override
    protected Controller<EndRoundMenu> getController() {
        return new EndStateController(getModel());
    }
}
