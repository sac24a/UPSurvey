package com.candlestickschart.upsurvey;

public class LocationData {
    String id;
    String LOC_ID;
    String Name;
    String Voters;
    String Latitude;
    String Longitude;
    public LocationData(String id,
            String LOC_ID,
            String Name,
            String Voters,
            String Latitude,
            String Longitude) {
        this.id = id;
        this.Latitude = Latitude;
        this.LOC_ID = LOC_ID;
        this.Name = Name;
        this.Voters = Voters;
        this.Longitude = Longitude;

    }
}
