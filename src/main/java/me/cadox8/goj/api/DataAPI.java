package me.cadox8.goj.api;

import lombok.Data;
import lombok.ToString;
import me.cadox8.goj.city.CityData;

@Data
@ToString
public class DataAPI {

    private long seed;
    private long creationDate;
    private String worldName;

    private CityData cityData;

    private int population;
/*    private Building[] buildings;
    private People[] people;
    private Disease[] diseases;*/ // class javax.swing.JFrame declares multiple JSON fields named state
}
