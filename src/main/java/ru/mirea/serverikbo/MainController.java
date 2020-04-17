package ru.mirea.serverikbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private CountyRespository cr;
    @RequestMapping(path="/")
    public String index() {
        return "index";
    }

    @GetMapping(path="/list")
    public @ResponseBody
    Iterable<Country> getAllCountries(){
        return cr.findAll();
    }

    @PostMapping(path="/add")
    public @ResponseBody String addNewCountry(@RequestParam String name){
        Country country = new Country();
        country.setName(name);
        cr.save(country);
        return "Saved!";
    }

    @GetMapping(path="/countryname")
    public String test(@RequestParam (name="name",required = false, defaultValue = "Default country") String name,Country country){
        country.setName(name);
        return "country";
    }
}
