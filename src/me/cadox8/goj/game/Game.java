package me.cadox8.goj.game;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.api.GameAPI;
import me.cadox8.goj.display.Display;
import me.cadox8.goj.gfx.textures.Assets;
import me.cadox8.goj.input.KeyManager;
import me.cadox8.goj.input.MouseManager;
import me.cadox8.goj.state.MenuState;
import me.cadox8.goj.state.State;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {

    private final String title;
    @Getter private final int width, height;

    @Getter private static GameAPI gameAPI;

    @Getter private Display display;

    // Utils
    @Getter @Setter private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;
    //

    @Getter private State menuState;

    // Input
    @Getter private final KeyManager keyManager;
    @Getter private final MouseManager mouseManager;

    public Game(String name, int width, int height) {
        this.title = name;
        this.width = width;
        this.height = height;

        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseWheelListener(mouseManager);

        gameAPI = new GameAPI(this);

        Assets.init();

        menuState = new MenuState(gameAPI);

        State.setState(menuState);
    }


    private void tick() {
        keyManager.tick();
        State.getState().tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);

        State.getState().render(g);

        bs.show();
        g.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerTick = (float)1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                //Log.log("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
