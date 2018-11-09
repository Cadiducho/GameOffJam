package me.cadox8.goj.state;

import me.cadox8.goj.Launcher;
import me.cadox8.goj.api.GameAPI;
import me.cadox8.goj.ui.UIImageButton;
import me.cadox8.goj.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(GameAPI gameAPI) {
        super(gameAPI);

        uiManager = new UIManager(gameAPI);
        gameAPI.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(150, 650, 200, 100, null, () -> {
            gameAPI.getMouseManager().setUIManager(null);
        }));

        uiManager.addObject(new UIImageButton(900, 650, 200, 100, null, () -> System.exit(0)));
    }



    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
        g.setColor(Color.BLACK);
        g.drawString("Version: " + Launcher.getVersion(), 5, 795);
    }
}
