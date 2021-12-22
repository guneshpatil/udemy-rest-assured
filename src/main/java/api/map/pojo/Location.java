package api.map.pojo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    @JsonProperty("lat")
    @JsonAlias("latitude")
    public double Lat;

    @JsonProperty("lng")
    @JsonAlias("longitude")
    public double Lng;

    public Location() {
    }

    public Location(double lat, double lng) {
        Lat = lat;
        Lng = lng;
    }
}
