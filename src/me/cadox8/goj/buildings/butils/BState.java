package me.cadox8.goj.buildings.butils;

import lombok.Getter;
import lombok.Setter;

public abstract class BState {

    @Getter @Setter private State buildingState = State.EMPTY;

    protected boolean needWater = false;
    protected boolean needToBeRemoved = false;
    protected boolean needToBeFilled = true;

    public enum State {
        ONFIRE, BURNED, EMPTY, ABANDONED, GOOD
    }
}
