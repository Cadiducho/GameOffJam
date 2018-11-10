package me.cadox8.goj.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.buildings.house.HouseBuilding;
import me.cadox8.goj.city.City;

public abstract class Entity {

    @Getter private final String name;

    @Getter @Setter private HouseBuilding house;

    public Entity() {
        this.name = "";

        house = null;
    }

    public boolean moveHouse(City city, HouseBuilding newHouse) {
        if (newHouse.getPeopleInBuilding() > newHouse.getMaxPopulation()) return false;

        if (house != null) city.removePopulation(house);
        city.addPopulation(newHouse);

        return true;
    }
}
