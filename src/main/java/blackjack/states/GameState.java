package blackjack.states;

import blackjack.controller.Controller;
import blackjack.controller.game.TableController;
import blackjack.model.game.table.Table;
import blackjack.viewer.Viewer;
import blackjack.viewer.game.GameViewer;

public class GameState extends State<Table> {

    public GameState(Table table) {
        super(table);
    }

    @Override
    protected Viewer<Table> getViewer() {
        return new GameViewer(getModel());
    }

    @Override
    protected Controller<Table> getController() {
        return new TableController(getModel());
    }
}
