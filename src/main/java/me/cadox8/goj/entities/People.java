package me.cadox8.goj.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.buildings.house.HouseBuilding;
import me.cadox8.goj.city.City;

public class People extends Entity {

    @Getter @Setter private HouseBuilding house;

    public People() {
        house = null;
    }

    public boolean asingHouse(HouseBuilding house) {
        if (this.house != null) return false;
        setHouse(house);
        house.getPeopleInside().add(this);
        return true;
    }

    public boolean moveHouse(City city, HouseBuilding newHouse) {
        if (newHouse.getPeopleInBuilding() > newHouse.getMaxPopulation()) return false;

        if (house != null) city.removePopulation(house);
        city.addPopulation(newHouse);
        house = newHouse;
        return true;
    }
}
