package me.cadox8.goj.gfx.textures;

import me.cadox8.goj.gfx.Sprites;
import me.cadox8.goj.utils.Utils;

import java.awt.image.BufferedImage;

public class Assets {

    public static final int WIDTH = 32, HEIGHT = 32;
    private static Sprites sprites;

    public static BufferedImage newGame, loadGame;

    public static BufferedImage house;

    public static BufferedImage[] door;

    public static void init() {
        sprites = new Sprites(Utils.loadImage("/utils/menu.png"));
        newGame = getImage(0, 0, 270, 73);

        sprites = new Sprites(Utils.loadImage("/textures/house.png"));

        house = getImage(0, 0, 214, 182);

        sprites = new Sprites(Utils.loadImage("/textures/door.png"));
        door = new BufferedImage[6];
        door[0] = getImage(0, 0, 32, 64);
        door[1] = getImage(1, 0, 32, 64);
        door[2] = getImage(2, 0, 32, 64);
        door[3] = getImage(3, 0, 32, 64);
        door[4] = getImage(4, 0, 32, 64);
        door[5] = getImage(5, 0, 32, 64);
    }

    private static BufferedImage getImage(int x, int y) {
        return getImage(x, y, WIDTH, HEIGHT);
    }

    private static BufferedImage getImage(int x, int y, int width, int height) {
        return sprites.crop(width * x, height * y, width, height);
    }
}
