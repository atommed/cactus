package io.github.interstell.cactus.backend.api;

import io.github.interstell.cactus.backend.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
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
    String resp;

    @RequestMapping("/lol")
    @ResponseBody
    public String lol(){
        return "S";
    }

    @RequestMapping("/db")
    @ResponseBody
    public List<Event> db(){
        List<Event> res = this.jdbc.query("select * from event" +
                " where CAST(date as DATE) < '2016-05-25' and CAST(date as DATE) > current_date",((ResultSet rs, int rowNum) -> {
            Event ev = new Event();
            ev.setId(rs.getInt("id"));
            ev.setName(rs.getString("name"));
            ev.setDescription(rs.getString("description"));
            ev.setFree(rs.getBoolean("free"));
            ev.setPrice(rs.getDouble("price"));
            return ev;
        }));
        return res;
    }

    @RequestMapping("/k")
    @ResponseBody
    public String t(){
        System.out.println("lel");
        return resp;
    }
}
