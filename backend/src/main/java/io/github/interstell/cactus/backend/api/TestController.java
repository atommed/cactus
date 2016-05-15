package io.github.interstell.cactus.backend.api;

import io.github.interstell.cactus.backend.models.Event;
import io.github.interstell.cactus.backend.models.EventRowMapper;
import io.github.interstell.cactus.backend.util.GoogleGeocode.LocationFinder;
import io.github.interstell.cactus.backend.util.GooglePlaces.PlacesFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    String resp;


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
                " where CAST(date as DATE) < '2016-05-25' and CAST(date as DATE) > current_date",
                new EventRowMapper());
        return res;
    }

    @RequestMapping("/raiting")
    @ResponseBody
    public List<Event> likes(){
        List<Event> res = this.jdbc.query("select * from event" +
                " order by -likes limit 10", new EventRowMapper());

        return res;
    }

    @RequestMapping("/k")
    @ResponseBody
    public String t(){
        System.out.println("lel");
        return resp;
    }
}
