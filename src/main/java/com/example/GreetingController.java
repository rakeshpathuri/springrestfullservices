package com.example;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "%s";
    private final AtomicLong counter = new AtomicLong();
    private ArrayList<Greeting> g = new ArrayList<Greeting>();
    Greeting as = new Greeting(1,"dddd");

    @RequestMapping(value = "/add")
    public void addgreeting(@RequestParam(value="name", defaultValue="World") String[] name)
    {
        if(name.length ==1)
        g.add(new Greeting(counter.incrementAndGet(),String.format(template, name)));
        else
            for (String n:name)
            {
                g.add(new Greeting(counter.incrementAndGet(),String.format(template, n)));
            }

    }

    @RequestMapping(value = "/greeting",produces = {
            MediaType.APPLICATION_JSON_VALUE,},method= RequestMethod.GET)
    public List<Greeting> greetall()
    {
        g.add(as);
        return g;
    }
    @RequestMapping(value = "/getbyname",method = RequestMethod.GET)
    public ResponseEntity<Greeting> getbyname(@RequestParam(value = "name") String name)
    {

        for(Greeting user : g){
            if(user.getContent().equalsIgnoreCase(name)){
                return new ResponseEntity<Greeting>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Greeting>(new Greeting(1,"rrrr"), HttpStatus.OK);
    }

    @RequestMapping(value ="/update",method = RequestMethod.PUT)
    public void update(@RequestParam(value = "id") long id,@RequestBody Greeting u)
    {
        for(Greeting user : g){
            if(user.getId() == id)
            {
                 user.setContent(u.getContent());
            }

        }

    }

    @RequestMapping(value ="/delelte",method = RequestMethod.DELETE)
    public void delete(@RequestParam(value = "id") long id,@RequestBody Greeting u)
    {
        for(Greeting user : g){
            if(user.getId() == id)
            {
                g.remove(user);
            }

        }

    }

}