package io.github.interstell.cactus.backend.api;

import io.github.interstell.cactus.backend.models.Event;
import io.github.interstell.cactus.backend.models.EventRowMapper;
import io.github.interstell.cactus.backend.util.GoogleGeocode.LocationFinder;
import io.github.interstell.cactus.backend.util.GooglePlaces.PlacesFinder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Grigoriy on 5/14/2016.
 */

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    PlacesFinder placesFinder;


    @RequestMapping(value = "/map/{addr}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getCoords(@PathVariable("addr") String addr){
        return placesFinder.findPlaceInfo(addr);
    }

    @RequestMapping("/lol")
    @ResponseBody
    public String lol(){
        return "S";
    }

    @RequestMapping("/db")
    @ResponseBody
    public List<Event> db(){
        List<Event> res = this.jdbc.query("select * from event" +
                " where CAST(date as DATE) < '2016-05-25' and CAST(date as DATE) > current_date " +
                "order by id",
                new EventRowMapper());
        return res;
    }

    @RequestMapping("/raiting")
    @ResponseBody
    public List<Event> likes(){
        List<Event> res = this.jdbc.query("select * from event" +
                " order by -likes limit 3", new EventRowMapper());

        return res;
    }

    @RequestMapping("/fillDB")
    @ResponseBody
    public String fillDB(){
        JSONParser parser = new JSONParser();
        try {
            JSONObject root = (JSONObject) parser.parse(new FileReader("C:\\Users\\Grigoriy\\Documents\\cactus\\backend\\src\\main\\resources\\test.json"));
            JSONArray resp = (JSONArray) root.get("response");

            for(int i = 1; i < resp.size(); i++){
                JSONObject res = (JSONObject) resp.get(i);
                JSONObject attachments = (JSONObject) res.get("attachment");
                JSONObject photo = (JSONObject) attachments.get("photo");
                String text = res.get("text").toString();
                String img_uri = photo.get("src_big").toString();

                jdbc.update("insert into event (name, description,free, img, likes) " +
                        "values ('test', 'nonparsed untill tomita aga', FALSE, ?, -5)",img_uri);

                System.out.println(img_uri);
            }

            System.out.println("Good");
            return Integer.toString(resp.size());
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
