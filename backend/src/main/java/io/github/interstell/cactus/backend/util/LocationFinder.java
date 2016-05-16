package io.github.interstell.cactus.backend.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Grigoriy on 5/14/2016.
 */

class Geomtry{
    public Geomtry(){

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    double lat;
    double lng;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Location{
    Geomtry geomtry;
}

public class LocationFinder {
    RestTemplate rest = new RestTemplate();

    String key = "AIzaSyACmesrYQjwH0-W5FaULoK6-o8A2NC5GyM";

    public LocationFinder(){

    }
    public String findLocation(String address){
        Map<String, String> vars = new HashMap<>();

        vars.put("address", address);
        //vars.put("address","ул. Политехническая");
        vars.put("key", "AIzaSyAAqEkiSuywKT7O2DgFZIHoErePacDc7iY");

        String url = "https://maps.googleapis.com/maps/api/geocode/json" +
                "?address={address}&key={key}&language=ru";
        String l = rest.getForObject(url, String.class, vars);
        return l;
    }
}
