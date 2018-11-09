package me.cadox8.goj.buildings;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.buildings.butils.BState;
import me.cadox8.goj.events.building.BuildingStateChangeEvent;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public abstract class Building {

    @Getter @Setter protected BuildingType buildingType;
    @Getter @Setter protected int level;
    @Getter @Setter protected BState buildingState;
    @Getter protected BuildingIcons icons;

    @Getter @Setter protected int happiness;

    public Building(BuildingType buildingType, int level, BState buildingState) {
        this.buildingType = buildingType;
        this.level = level <= 0 || level >= 3 ? 1 : level;
        this.buildingState = buildingState;

        icons = new BuildingIcons();
    }

    public abstract void work();

    public void changeBuildingState(BState newState) {
        new BuildingStateChangeEvent(this, buildingState, newState);
    }

    public Building addIcon(BState.State bstate, BufferedImage icon) {
        icons.addIcon(bstate, icon);
        return this;
    }


    public enum BuildingType {
        HOUSE, SHOP, FACTORY, UKNOWN
    }



    private class BuildingIcons {
        private HashMap<BState.State, BufferedImage> icons;

        private BuildingIcons() {
            icons = new HashMap<>();
        }

        private void addIcon(BState.State bstate, BufferedImage icon) {
            icons.put(bstate, icon);
        }

        public BufferedImage getImage(BState.State bstate) {
            return icons.getOrDefault(bstate, null);
        }
    }
}
