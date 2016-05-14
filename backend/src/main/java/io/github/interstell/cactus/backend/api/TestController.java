package io.github.interstell.cactus.backend.api;

import io.github.interstell.cactus.backend.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Grigoriy on 5/14/2016.
 */

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    String resp;

    @RequestMapping("/lol")
    @ResponseBody
    public String lol(){
        return "S";
    }

    @RequestMapping("/k")
    @ResponseBody
    public String t(){
        System.out.println("lel");
        return resp;
    }

    @RequestMapping("/json")
    public @ResponseBody Event getEvents(){
        return new Event("hhh", 2);
    }
}
