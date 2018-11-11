package me.cadox8.goj.city;

import lombok.Data;

import java.util.Random;

@Data
public class CityData {

    private int waterAmount;
    private int woodAmount;


    public CityData() {
        final Random r = new Random();

        waterAmount = r.nextInt(500) + 125;
        woodAmount = r.nextInt(600) + 100;
    }
}
