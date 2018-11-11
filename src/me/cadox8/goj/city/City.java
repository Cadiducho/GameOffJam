package me.cadox8.goj.city;

import lombok.Getter;
import me.cadox8.goj.buildings.Building;
import me.cadox8.goj.buildings.house.HouseBuilding;
import me.cadox8.goj.buildings.services.Service;
import me.cadox8.goj.entities.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class City {

    @Getter private String cityName;

    @Getter private double money;

    @Getter private ArrayList<Building> buildings;

    @Getter private ArrayList<Entity> population;

    public City(String cityName) {
        this.cityName = cityName;
        this.money = 2500;
        this.buildings = new ArrayList<>();
        this.population = new ArrayList<>();
    }

    public boolean buildHouse(int level) {
        double cost = getBuildCost(level, Building.BuildingType.HOUSE);

        if (money - cost < 0) return false;
        buildings.add(new HouseBuilding(level));
        return true;
    }

    public boolean removePopulation(HouseBuilding houseBuilding) {
        return removePopulation(houseBuilding, 1);
    }
    public boolean removePopulation(HouseBuilding houseBuilding, int amount) {
        if (houseBuilding.getPeopleInBuilding() - amount < 0) return false;
        for (int x = 0; x < amount; x++) {
            houseBuilding.getPeopleInside().remove(x);
            houseBuilding.setHappiness(houseBuilding.getHappiness() - 1);
        }

        if (houseBuilding.getPeopleInBuilding() == 0) houseBuilding.setBuildingState(Building.BuildingState.ABANDONED);
        return true;
    }

    public boolean addPopulation(HouseBuilding houseBuilding) {
        return addPopulation(houseBuilding, 1);
    }
    public boolean addPopulation(HouseBuilding houseBuilding, int amount) {
        if (houseBuilding.getPeopleInBuilding() + amount > houseBuilding.getMaxPopulation()) return false;

        for (int x = 0; x < amount; x++) {
            Entity en = population.stream().filter(p -> p.getHouse() == null).findFirst().get();
            if (en == null) return false;
            en.setHouse(houseBuilding);
            houseBuilding.getPeopleInside().add(en);
            houseBuilding.setHappiness(houseBuilding.getHappiness() + 1);
        }
        return true;
    }

    public void addMoney(double amount) {
        money += amount;
    }
    public void removeMoney(double amount) {
        money -= amount;
    }

    public double getBuildCost(int level, Building.BuildingType buildingType) {
        switch (buildingType) {
            case HOUSE:
                return (level * 0.34) + 40;
            case FACTORY:
                return (level * 0.34) + 60;
            case SHOP:
            case SERVICES:
                return (level * 0.34) + 30;
            default:
                return 0;
        }
    }

    public List<Building> getBuildingByType(Building.BuildingType type) {
        return buildings.stream().filter(b -> b.getBuildingType() == type).collect(Collectors.toList());
    }

    public List<Service> getServicesByType(Service.ServiceType type) {
        List<Service> ser = new ArrayList<>();
        getBuildingByType(Building.BuildingType.SERVICES).stream().filter(s -> ((Service)s).getServiceType() == type).collect(Collectors.toList()).forEach(s -> ser.add((Service)s));
        return ser;
    }
}
