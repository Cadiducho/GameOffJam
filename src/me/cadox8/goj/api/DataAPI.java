package me.cadox8.goj.api;

import lombok.Data;
import me.cadox8.goj.buildings.Building;

@Data
public class DataAPI {

    private long seed;
    private long creationDate;
    private String worldName;

    private int population;
    private Building[] buildings;
}
