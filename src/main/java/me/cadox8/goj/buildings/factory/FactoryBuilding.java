package me.cadox8.goj.buildings.factory;

import lombok.Getter;
import me.cadox8.goj.buildings.Building;

public class FactoryBuilding extends Building {

    @Getter private int maxPopulation;

    public FactoryBuilding(int level) {
        super(BuildingType.FACTORY, level, BuildingState.EMPTY);

        setMaxPopulation();

        // Icons
        addIcon(BuildingState.GOOD, null);
    }

    @Override
    public void work() {
        gameAPI.getCity().addMoney(20 + (level * 3));
    }

    private void setMaxPopulation() {
        switch (level) {
            case 1:
                maxPopulation = 6;
                break;
            case 2:
                maxPopulation = 8;
                break;
            case 3:
                maxPopulation = 12;
                break;

            default:
                level = 1;
                maxPopulation = 6;
                break;
        }
    }
}
