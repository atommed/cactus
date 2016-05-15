package io.github.interstell.cactus.backend.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.github.interstell.cactus.backend.models.Event;
import io.github.interstell.cactus.backend.models.EventRowMapper;
import io.github.interstell.cactus.backend.util.GoogleGeocode.LocationFinder;
import io.github.interstell.cactus.backend.util.GooglePlaces.PlacesFinder;
import io.github.interstell.cactus.backend.util.TomitaRunner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by Grigoriy on 5/14/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
class NewEventFormData{
    String name;
    String place;
    String time;
    String imgLink;
    String description;
}

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    TomitaRunner tomita;

    @Autowired
    JdbcTemplate jdbc;
    @Autowired
    PlacesFinder placesFinder;

    @RequestMapping(value = "/addEvent", method = {RequestMethod.POST, RequestMethod.OPTIONS, RequestMethod.GET})
    @ResponseBody
    public String getEvent(@RequestParam("json") NewEventFormData form){
        return "ok";
    }

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

    private String getName(String text){
        int i=0;
        String res="";
        while(text.charAt(i) !='|' && i < text.length())
            i++;
        i++;
        while(text.charAt(i) != ']' && i < text.length()) { res+=text.charAt(i);i++;}
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
                Event event = new Event();

                JSONObject res = (JSONObject) resp.get(i);
                JSONObject attachments = (JSONObject) res.get("attachment");
                JSONObject photo = (JSONObject) attachments.get("photo");
                String text = res.get("text").toString();
                text = text.replaceAll("<br>"," ");
                System.out.println(getName(text));
                String img_uri = photo.get("src_big").toString();
                System.out.println(img_uri);

                tomita.parse(text,event);

                //jdbc.update("insert into event (name, description,free, img, likes) " +
                //        "values ('test', 'nonparsed untill tomita aga', FALSE, ?, -5)",img_uri);

                //System.out.println(img_uri);
            }
            return Integer.toString(resp.size());
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (ParseException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return "we";
    }
}
