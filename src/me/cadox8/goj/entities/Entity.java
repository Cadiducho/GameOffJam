package me.cadox8.goj.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.api.GameAPI;
import me.cadox8.goj.buildings.house.HouseBuilding;
import me.cadox8.goj.buildings.services.Service;
import me.cadox8.goj.city.City;
import me.cadox8.goj.diseases.Disease;
import me.cadox8.goj.game.Game;
import me.cadox8.goj.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

public abstract class Entity {

    private final GameAPI gameAPI = Game.getGameAPI();

    @Getter private final String name;
    @Getter private int age;

    @Getter @Setter private HouseBuilding house;

    @Getter @Setter private ArrayList<Disease> diseases;

    public Entity() {
        this.name = Utils.citizenNameGenerator();
        this.age = 0;

        house = null;
        diseases = new ArrayList<>();
    }

    public boolean moveHouse(City city, HouseBuilding newHouse) {
        if (newHouse.getPeopleInBuilding() > newHouse.getMaxPopulation()) return false;

        if (house != null) city.removePopulation(house);
        city.addPopulation(newHouse);
        house = newHouse;
        return true;
    }

    public void die() {
        gameAPI.getCity().removePopulation(house);
        gameAPI.getCity().getPopulation().remove(this);

        // When someone dies, city must pay something to the family, so
        gameAPI.getCity().removeMoney(30);
    }

    public void happyBirthday() {
        age++;

        if (age > 25 + (Service.ratioPeopleHospitals() * 10)) {
            int prob = (int)(age * 0.21) - (Service.ratioPeopleHospitals() * 10);

            if (!diseases.isEmpty()) for (Disease disease : diseases) prob += 0.43 * disease.getDangerLevel();
            if (new Random().nextInt(101) >= prob) die();
        }
    }
}
