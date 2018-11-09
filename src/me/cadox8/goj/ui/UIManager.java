package me.cadox8.goj.ui;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.api.GameAPI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UIManager {

    private GameAPI gameAPI;
    @Getter @Setter private ArrayList<UIObject> objects;

    public UIManager(GameAPI gameAPI) {
        this.gameAPI = gameAPI;
        objects = new ArrayList<>();
    }

    public void tick() {
        objects.stream().filter(UIObject::isEnabled).forEach(UIObject::tick);
    }

    public void render(Graphics g) {
        objects.stream().filter(UIObject::isEnabled).forEach(o -> o.render(g));
    }

    public void onMouseMove(MouseEvent e) {
        objects.forEach(o -> o.onMouseMove(e));
    }

    public void onMouseRelease(MouseEvent e) {
        objects.forEach(o -> o.onMouseRelease(e));
    }

    public void addObject(UIObject o) {
        objects.add(o);
    }
    public void removeObject(UIObject o) {
        objects.remove(o);
    }
}
