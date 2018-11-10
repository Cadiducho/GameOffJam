package me.cadox8.goj.buildings.house;

import lombok.Getter;
import me.cadox8.goj.buildings.Building;
import me.cadox8.goj.gfx.textures.Assets;

public class HouseBuilding extends Building {

    @Getter private int maxPopulation;

    public HouseBuilding(int level) {
        super(BuildingType.HOUSE, level, BuildingState.EMPTY);

        setMaxPopulation();

        // Icons
        addIcon(BuildingState.GOOD, Assets.house);
    }

    @Override
    public void work() {

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
