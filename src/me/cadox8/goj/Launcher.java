package me.cadox8.goj;

import lombok.Getter;
import me.cadox8.goj.game.Game;

public class Launcher {

    @Getter private static final String version = "v0.0.1 Alpha";

    public static void main(String... args) {
        Game game = new Game("GameOffJam", 1200, 900);
        game.start();
    }
}
