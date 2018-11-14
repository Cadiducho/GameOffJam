package me.cadox8.goj.buildings.services;

import lombok.Getter;
import me.cadox8.goj.buildings.Building;

public abstract class Service extends Building {

    @Getter private final ServiceType serviceType;

    public Service(ServiceType serviceType) {
        super(BuildingType.SERVICES, 1, BuildingState.EMPTY);

        this.serviceType = serviceType;
    }

    public static int ratioPeopleHospitals() {
        try {
            return (gameAPI.getCity().getServicesByType(ServiceType.HOSPITAL).size() / gameAPI.getCity().getPopulation().size()) * 10;
        } catch (Exception e) {
            return 0;
        }
    }

    public enum ServiceType {
        POLICE, HOSPITAL, FIREFIGHTERS, TRANSPORT
    }
}
