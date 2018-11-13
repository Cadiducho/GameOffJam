package me.cadox8.goj.ui;

import lombok.Getter;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class UIImageButton extends UIImage {

    @Getter private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage image, ClickListener clicker) {
        this(x, y, width, height, Arrays.asList(image).toArray(new BufferedImage[1]), clicker);
    }
    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height, images);
        this.clicker = clicker;
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
