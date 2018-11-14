package me.cadox8.goj.city;

import lombok.Data;
import me.cadox8.goj.game.Game;

import java.util.Random;

@Data
public class CityData {

    private long seed;

    private long creationDate;

    private int waterAmount;
    private int woodAmount;

    public CityData() {}

    public CityData(int id) {
        final Random r = new Random();

        creationDate = System.currentTimeMillis();

        seed = r.nextLong();
        waterAmount = r.nextInt(500) + 125;
        woodAmount = r.nextInt(600) + 100;

        generateWorld();
    }

    private void generateWorld() {
        int wood = 0;
        int water = 0;

        for (int y = 0; y < Game.getGameAPI().getHeight(); y += 4) {
            for (int x = 0; x < Game.getGameAPI().getWidth(); x += 4) {
                switch (new Random().nextInt(12)) {
                    case 0:
                        water++;
                        break;
                    case 2:
                        wood++;
                        break;

                    default:
                        break;
                }
            }
        }
        woodAmount = wood;
        waterAmount = water;
    }
}
