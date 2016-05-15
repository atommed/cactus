package io.github.interstell.cactus.backend.util.GoogleGeocode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.ComponentFilter;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Grigoriy on 5/15/2016.
 */
public class LocationFinder {
    private GeoApiContext context;
    private ComponentFilter[] filters;

    String key = "AIzaSyAcnwLwEHqA0GaIuueVytfKXZHcYCTI-xY";
    RestTemplate rest = new RestTemplate();

    public LocationFinder(){
        context = new GeoApiContext().setApiKey(key);
        filters = new ComponentFilter[]{
                ComponentFilter.country("UA"),
                ComponentFilter.administrativeArea("город Киев")
        };
    }
    public String findLocation(String address){
        GeocodingResult[] results;
        try {
            results = new GeocodingApiRequest(context)
                    .language("ru")
                    .address(address)
                    .components(filters)
                    .await();
        } catch (Exception e) {
            return e.getMessage();
        }
        return  results[0].formattedAddress;
    }
}
