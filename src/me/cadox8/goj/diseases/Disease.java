package me.cadox8.goj.diseases;

import lombok.Getter;
import lombok.Setter;
import me.cadox8.goj.api.GameAPI;
import me.cadox8.goj.buildings.services.Service;
import me.cadox8.goj.entities.Entity;
import me.cadox8.goj.game.Game;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public abstract class Disease {

    private final GameAPI gameAPI = Game.getGameAPI();

    @Getter private final String diseaseName;
    @Getter private final int dangerLevel; // Max: 8

    @Getter private final double cureTime; // In seconds

    @Getter private ArrayList<Entity> affectedPeople;

    @Getter @Setter private BufferedImage icon;

    public Disease(String diseaseName, int dangerLevel, double cureTime) {
        this.diseaseName = diseaseName;
        this.dangerLevel = (dangerLevel > 8 | dangerLevel < 1 ? 1 : dangerLevel);
        this.cureTime = cureTime;

        affectedPeople = new ArrayList<>();
    }

    public boolean canBeCured() {
        final int ph = gameAPI.getCity().getPopulation().size() / gameAPI.getCity().getServicesByType(Service.ServiceType.HOSPITAL).size();
        return dangerLevel <= (ph > 8 ? 8 : ph);
    }

    public void getSick(int people) {
        for (int x = 0; x < people; x++) getSick();
    }
    public void getSick() {
        Entity entity = gameAPI.getCity().getPopulation().get(new Random().nextInt(gameAPI.getCity().getPopulation().size()));

        if (affectedPeople.containsAll(gameAPI.getCity().getPopulation())) return; // Don't want StackOverFlow Exceptions :D

        if (affectedPeople.contains(entity)) {
            getSick();
            return;
        }
        affectedPeople.add(entity);
    }
}
