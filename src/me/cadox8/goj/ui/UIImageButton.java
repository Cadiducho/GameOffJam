package me.cadox8.goj.ui;

import lombok.Getter;

import java.awt.image.BufferedImage;

public class UIImageButton extends UIImage {

    @Getter private ClickListener clicker;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
        super(x, y, width, height, images);
        this.clicker = clicker;
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
