package blackjack.states;

import blackjack.controller.Controller;
import blackjack.controller.menu.MenuController;
import blackjack.model.menu.Menu;
import blackjack.viewer.Viewer;
import blackjack.viewer.menu.MenuViewer;

public class MenuState extends State<Menu> {
    public MenuState(Menu model) {
        super(model);
    }

    @Override
    protected Viewer<Menu> getViewer() {
        return new MenuViewer(getModel());
    }

    @Override
    protected Controller<Menu> getController() {
        return new MenuController(getModel());
    }
}
