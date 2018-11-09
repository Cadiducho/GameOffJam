package me.cadox8.goj.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject {

    private BufferedImage[] images;

    private UIText noImage;

    public UIImage(float x, float y, int width, int height, BufferedImage[] images) {
        super(x, y, width, height);
        this.images = images;

        if (images == null) noImage = new UIText(x, y, Color.RED, "This image does not exists", this instanceof UIImageButton ? ((UIImageButton)this).getClicker() : () -> {});
    }

    @Override
    public void tick() {
        if (noImage != null) noImage.tick();
    }

    @Override
    public void render(Graphics g) {
        try {
            if (hovering) {
                if (images.length > 1) {
                    g.drawImage(images[1], (int) x, (int) y, width, height, null);
                } else {
                    g.drawImage(images[0], (int) x, (int) y, width + 5, height + 5, null);
                }
            } else {
                g.drawImage(images[0], (int) x, (int) y, width, height, null);
            }
        } catch (NullPointerException e) {
            noImage.render(g);
        }
    }

    @Override
    public void onClick() {}
}
