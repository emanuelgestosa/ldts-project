package blackjack.controller.game;

import blackjack.controller.Controller;
import blackjack.model.game.table.Table;

public abstract class GameController extends Controller<Table> {
    public GameController(Table table) {
        super(table);
    }
}
