package blackjack.states;

import blackjack.controller.Controller;
import blackjack.controller.menu.ConfigsMenuController;
import blackjack.model.menu.ConfigsMenu;
import blackjack.viewer.Viewer;
import blackjack.viewer.menu.ConfigsMenuViewer;

public class ConfigsMenuState extends State<ConfigsMenu> {
    public ConfigsMenuState(ConfigsMenu model) {
        super(model);
    }

    @Override
    protected Viewer<ConfigsMenu> getViewer() {
        return new ConfigsMenuViewer(getModel());
    }

    @Override
    protected Controller<ConfigsMenu> getController() {
        return new ConfigsMenuController(getModel());
    }
}
