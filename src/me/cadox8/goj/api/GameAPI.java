package me.cadox8.goj.api;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.city.City;
import me.cadox8.goj.game.Game;
import me.cadox8.goj.input.MouseManager;

public class GameAPI {

    private Game game;

    @Getter @Setter private boolean debug = false;

    public GameAPI(Game game) {
        this.game = game;
    }

    public void setCity(City city) {
        game.setCity(city);
    }
    public City getCity() {
        return game.getCity();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public int getWidth() {
        return game.getWidth();
    }
    public int getHeight() {
        return game.getHeight();
    }
}
