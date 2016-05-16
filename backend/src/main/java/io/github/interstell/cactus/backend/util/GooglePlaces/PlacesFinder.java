package io.github.interstell.cactus.backend.util.GooglePlaces;

import com.google.maps.GeoApiContext;
import com.google.maps.NearbySearchRequest;
import com.google.maps.PlacesApi;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Grigoriy on 5/15/2016.
 */
public class PlacesFinder {
    @Autowired
    GeoApiContext geo;

    public String findPlaceInfo(String name){
        PlacesSearchResponse resp;
        try {
            resp = PlacesApi.textSearchQuery(geo, name)
                    .language("ru")
                    .await();
        } catch (Exception e) {
            return e.getMessage();
        }
        return resp.results[0].formattedAddress;
    }
}
