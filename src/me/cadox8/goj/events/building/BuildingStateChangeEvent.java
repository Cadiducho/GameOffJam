package me.cadox8.goj.events.building;

import lombok.Getter;
import me.cadox8.goj.buildings.Building;

public class BuildingStateChangeEvent extends BuildingEvent {

    @Getter private Building.BuildingState oldState;
    @Getter private Building.BuildingState newState;

    public BuildingStateChangeEvent(Building building, Building.BuildingState oldState, Building.BuildingState newState) {
        super(building);

        this.oldState = oldState;
        this.newState = newState;

        if (oldState == Building.BuildingState.BURNED && newState == Building.BuildingState.ONFIRE) setCancelled(true);

        onEvent();
    }

    @Override
    public void onEvent() {
        if (isCancelled()) return;

        building.setBuildingState(newState);
    }
}
