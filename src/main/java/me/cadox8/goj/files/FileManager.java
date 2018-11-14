package me.cadox8.goj.files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import me.cadox8.goj.api.DataAPI;
import me.cadox8.goj.city.City;
import me.cadox8.goj.city.CityData;

import java.io.*;

public class FileManager {

    private static File f = new File("C:\\GameOffJam", "city.json");

    public static void checkFile() {
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) { }
    }

    public static boolean save(City city){
        final CityData cityData = city.getCityData();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            JsonObject data = new JsonObject();

            data.addProperty("worldName", city.getCityName());
            data.addProperty("seed", cityData.getSeed());
            data.addProperty("creationDate", cityData.getCreationDate());

            JsonObject cData = new JsonObject();
            cData.addProperty("water", cityData.getWaterAmount());
            cData.addProperty("wood", cityData.getWoodAmount());

            data.add("cityData", cData);

            data.addProperty("population", city.getPopulation().size());

            JsonArray buildings = new JsonArray();

            city.getBuildings().forEach(b -> {
                JsonObject build = new JsonObject();
                build.addProperty("buildingState", b.getBuildingState().toString());
                build.addProperty("buildingType", b.getBuildingType().toString());
                build.addProperty("peopleInBuilding", b.getPeopleInBuilding());
                build.addProperty("happiness", b.getHappiness());
                build.addProperty("level", b.getLevel());

                JsonArray people = new JsonArray();

                b.getPeopleInside().forEach(p -> {
                    JsonObject peop = new JsonObject();

                    peop.addProperty("name", p.getName());
                    peop.addProperty("age", p.getAge());

                    JsonArray diseases = new JsonArray();

                    p.getDiseases().forEach(d -> {
                        JsonObject dis = new JsonObject();

                        dis.addProperty("name", d.getDiseaseName());
                        dis.addProperty("cureTime", d.getCureTime());
                        dis.addProperty("dangerLevel", d.getDangerLevel());

                        diseases.add(dis);
                    });
                    peop.add("diseases", diseases);
                    people.add(peop);
                });
                build.add("people", people);
                buildings.add(build);
            });

            data.add("buildings", buildings);

            BufferedWriter w = new BufferedWriter(new FileWriter(f));

            if (f.exists()) f.delete(); f.mkdirs();

            w.write(gson.toJson(data));
            w.close();
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }


    public static DataAPI loadGame() {
        try {
            return new GsonBuilder().create().fromJson(new JsonReader(new FileReader(f)), DataAPI.class);
        } catch (IOException e) {
            e.printStackTrace();
            return new DataAPI();
        }
    }
}
