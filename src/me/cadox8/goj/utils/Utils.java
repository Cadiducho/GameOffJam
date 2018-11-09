package me.cadox8.goj.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Utils {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Utils.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(5);
            return null;
        }
    }
}
