package me.cadox8.goj.buildings.house;

import me.cadox8.goj.buildings.Building;
import me.cadox8.goj.buildings.butils.AbandonedState;

public abstract class HouseBuilding extends Building {

    protected int maxPopulation;
    protected int population;

    public HouseBuilding(int level) {
        super(BuildingType.HOUSE, level, null);

        setMaxPopulation();
        population = 0;
    }

    public boolean removePopulation() {
        if (population-- < 0) return false;
        population--;
        happiness--;
        if (population == 0) setBuildingState(new AbandonedState());
        return true;
    }

    public boolean addPopulation() {
        if (population++ > maxPopulation) return false;
        happiness++;
        population++;
        return true;
    }

    private void setMaxPopulation() {
        switch (level) {
            case 1:
                maxPopulation = 4;
                break;
            case 2:
                maxPopulation = 6;
                break;
            case 3:
                maxPopulation = 10;
                break;

            default:
                level = 1;
                maxPopulation = 4;
                break;
        }
    }
}
