package me.cadox8.goj.events.building;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.buildings.Building;
import me.cadox8.goj.events.Event;

public abstract class BuildingEvent extends Event {

    @Getter @Setter protected Building building;

    public BuildingEvent(Building building) {
        this.building = building;
    }
}
