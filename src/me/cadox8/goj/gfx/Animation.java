package me.cadox8.goj.gfx;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

public class Animation {

    @Getter @Setter private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    private boolean finished;

    public Animation(int speed, BufferedImage... frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();

        finished = false;
    }

    public void tick() {
        if (finished) return;
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= frames.length) {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    public void end() {
        finished = true;
    }

    public void reset() {
        finished = false;
    }
}
