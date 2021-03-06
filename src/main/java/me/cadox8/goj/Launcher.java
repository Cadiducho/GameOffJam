package me.cadox8.goj;

import lombok.Getter;
import me.cadox8.goj.files.FileManager;
import me.cadox8.goj.game.Game;

public class Launcher {

    @Getter private static final String version = "v0.0.5 Alpha";

    public static void main(String... args) {
        Game game = new Game("GameOffJam", 1200, 900);

        FileManager.checkFile();

        System.out.println(FileManager.loadGame().toString());

        game.start();
    }
}
