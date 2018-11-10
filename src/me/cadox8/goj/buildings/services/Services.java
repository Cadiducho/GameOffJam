package me.cadox8.goj.buildings.services;

import me.cadox8.goj.buildings.Building;

public abstract class Services extends Building {

    public Services() {
        super(BuildingType.SERVICES, 1, BuildingState.EMPTY);
    }
}
