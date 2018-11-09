package me.cadox8.goj.events.building;

import lombok.Getter;
import me.cadox8.goj.buildings.Building;
import me.cadox8.goj.buildings.butils.BState;

public class BuildingStateChangeEvent extends BuildingEvent {

    @Getter private BState oldState;
    @Getter private BState newState;

    public BuildingStateChangeEvent(Building building, BState oldState, BState newState) {
        super(building);

        this.oldState = oldState;
        this.newState = newState;

        if (oldState.getBuildingState() == BState.State.BURNED && newState.getBuildingState() == BState.State.ONFIRE) setCancelled(true);

        onEvent();
    }

    @Override
    public void onEvent() {
        if (isCancelled()) return;

        building.setBuildingState(newState);
    }
}
