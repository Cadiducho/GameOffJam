package me.cadox8.goj.buildings;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.entities.Entity;
import me.cadox8.goj.events.building.BuildingStateChangeEvent;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Building {

    @Getter @Setter protected BuildingType buildingType;
    @Getter @Setter protected int level;
    @Getter @Setter protected BuildingState buildingState;
    @Getter protected BuildingIcons icons;

    @Getter protected ArrayList<Entity> peopleInside;

    @Getter @Setter protected int happiness;

    public Building(BuildingType buildingType, int level, BuildingState buildingState) {
        this.buildingType = buildingType;
        this.level = level <= 0 || level >= 3 ? 1 : level;
        this.buildingState = buildingState;

        peopleInside = new ArrayList<>();

        icons = new BuildingIcons();
    }

    public abstract void work();

    public void changeBuildingState(BuildingState newState) {
        new BuildingStateChangeEvent(this, buildingState, newState);
    }

    public Building addIcon(BuildingState bstate, BufferedImage icon) {
        icons.addIcon(bstate, icon);
        return this;
    }

    public int getPeopleInBuilding() {
        return peopleInside.size();
    }


    public enum BuildingType {
        HOUSE, SHOP, FACTORY, SERVICES, UKNOWN
    }

    public enum BuildingState {
        ONFIRE, BURNED, EMPTY, ABANDONED, GOOD
    }



    private class BuildingIcons {
        private HashMap<BuildingState, BufferedImage> icons;

        private BuildingIcons() {
            icons = new HashMap<>();
        }

        private void addIcon(BuildingState bstate, BufferedImage icon) {
            icons.put(bstate, icon);
        }

        public BufferedImage getImage(BuildingState bstate) {
            return icons.getOrDefault(bstate, null);
        }
    }
}
