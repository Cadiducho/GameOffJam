package me.cadox8.goj.entities;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.api.GameAPI;
import me.cadox8.goj.buildings.services.Service;
import me.cadox8.goj.diseases.Disease;
import me.cadox8.goj.game.Game;
import me.cadox8.goj.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

public abstract class Entity {

    protected final GameAPI gameAPI = Game.getGameAPI();

    @Getter private final String name;
    @Getter private int age;

    @Getter @Setter private ArrayList<Disease> diseases;

    public Entity() {
        this.name = Utils.citizenNameGenerator();
        this.age = 1;
        diseases = new ArrayList<>();
    }

    public void die() {
        if (this instanceof People) gameAPI.getCity().removePopulation(((People) this).getHouse());
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
