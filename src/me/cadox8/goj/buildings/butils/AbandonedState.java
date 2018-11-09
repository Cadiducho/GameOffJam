package me.cadox8.goj.buildings.butils;

public class AbandonedState extends BState {

    public AbandonedState() {
        needToBeFilled = false;
        needToBeRemoved = true;
    }
}
