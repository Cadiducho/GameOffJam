package me.cadox8.goj.events;

import lombok.Getter;
import lombok.Setter;

public abstract class Event {

    private String name;

    @Getter @Setter private boolean cancelled = false;

    public abstract void onEvent();

    public String getEventName() {
        if (name == null) name = getClass().getSimpleName();
        return name;
    }
}
