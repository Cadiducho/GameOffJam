package me.cadox8.goj.display;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class Display {

    private final String title;
    private final int width, height;

    @Getter private JFrame frame;
    @Getter private Canvas canvas;

    public Display(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        frame.setUndecorated(false);
        frame.setSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        //frame.setIconImage(Utils.loadImage("/utils/icon.png"));

        frame.add(canvas);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
