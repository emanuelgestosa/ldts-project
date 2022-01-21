package blackjack.states;

import blackjack.controller.Controller;
import blackjack.controller.game.TableSplitController;
import blackjack.model.game.table.Table;
import blackjack.viewer.Viewer;
import blackjack.viewer.game.GameViewer;

public class GameSplitState extends State<Table>{

    public GameSplitState(Table model) {
        super(model);
    }

    @Override
    protected Viewer<Table> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Table> getController() {
        return new TableSplitController(getModel());
    }
}
