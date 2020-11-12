package com.lambdaschool.foundation.controllers;

import com.lambdaschool.foundation.models.*;
import com.lambdaschool.foundation.services.CityService;
import com.lambdaschool.foundation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//import com.lambdaschool.foundation.models.CityIdName;

@RestController
@RequestMapping("/cities")
public class CityController
{
    /**
     * Conenction to city services
     */
    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    /**
     *  /all endpont (Not enough memory in free tier of
     *  Heroku to use with fully populated DB. Disabled until
     *  resources are available)
     * @return list of all cities
     */
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> listAllCities(){
        List<City> cities = cityService.findAll();
        List<String> citynames = cityNamesFromCities(cities);
        return new ResponseEntity<>(citynames, HttpStatus.OK);
    }

    private List<String> cityNamesFromCities(List<City> cities){
        List<String> rtn = new ArrayList<>();
        for (City c : cities){
            rtn.add(c.getName());
        }
        return rtn;
    }

    @GetMapping(value = "/all-long",
       produces = "application/json")
    public ResponseEntity<?> listAllCitiesLong()
    {
        List<City> cities = cityService.findAll();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    /**
     * /city/{cityid} endpoint
     * @param id cityid
     * @return city object matching cityid or throws exception
     */
    @GetMapping(value = "/city/{id}", produces = "application/json")
    public ResponseEntity<?> getCityById(@PathVariable Long id)
    {
        City c = cityService.findCityById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    /**
     * /avg endpoint
     * @return City with average fields of all cities
     */
    @GetMapping(value = "/avg", produces = "application/json")
    public ResponseEntity<?> getAverageCity()
    {
        City c = cityService.returnAverageCity();

        return new ResponseEntity<>(c,HttpStatus.OK);
    }

    @GetMapping(value = "/filter", produces = "application/json")
    public ResponseEntity<?> getFilteredCities(
            @RequestBody CityFilter cityFilter
            ){
        List<City> cities = cityService.findAll();
        MinMaxInt popMinMax = cityFilter.getPopulation();
        if (popMinMax != null){
            for (int i = 0; i < cities.size(); i++){
                if (popMinMax.getMin() > cities.get(i).getPopulation()){
                    cities.remove(i);
                    i--;
                } else if (popMinMax.getMax() < cities.get(i).getPopulation()){
                    cities.remove(i);
                    i--;
                }
            }
        }
        MinMaxInt studioMinMax = cityFilter.getStudio();
        if (studioMinMax != null){
            for (int i = 0; i < cities.size(); i++){
                if (studioMinMax.getMin() > cities.get(i).getStudio()){
                    cities.remove(i);
                    i--;
                } else if (studioMinMax.getMax() < cities.get(i).getStudio()){
                    cities.remove(i);
                    i--;
                }
            }
        }
        MinMaxInt onebrMinMax = cityFilter.getOnebr();
        if (onebrMinMax != null){
            for (int i = 0; i < cities.size(); i++){
                if (onebrMinMax.getMin() > cities.get(i).getOnebr()){
                    cities.remove(i);
                    i--;
                } else if (onebrMinMax.getMax() < cities.get(i).getOnebr()){
                    cities.remove(i);
                    i--;
                }
            }
        }
        MinMaxInt twobrMinMax = cityFilter.getTwobr();
        if (twobrMinMax != null){
            for (int i = 0; i < cities.size(); i++){
                if (twobrMinMax.getMin() > cities.get(i).getTwobr()){
                    cities.remove(i);
                    i--;
                } else if (twobrMinMax.getMax() < cities.get(i).getTwobr()){
                    cities.remove(i);
                    i--;
                }
            }
        }
        MinMaxInt threebrMinMax = cityFilter.getThreebr();
        if (threebrMinMax != null){
            for (int i = 0; i < cities.size(); i++){
                if (threebrMinMax.getMin() > cities.get(i).getThreebr()){
                    cities.remove(i);
                    i--;
                } else if (threebrMinMax.getMax() < cities.get(i).getThreebr()){
                    cities.remove(i);
                    i--;
                }
            }
        }
        MinMaxInt fourbrMinMax = cityFilter.getFourbr();
        if (fourbrMinMax != null){
            for (int i = 0; i < cities.size(); i++){
                if (fourbrMinMax.getMin() > cities.get(i).getFourbr()){
                    cities.remove(i);
                    i--;
                } else if (fourbrMinMax.getMax() < cities.get(i).getFourbr()){
                    cities.remove(i);
                    i--;
                }
            }
        }
        MinMaxDbl hourlyMinMax = cityFilter.getHourly_wage();
        if (hourlyMinMax != null){
            for (int i = 0; i < cities.size(); i++){
                if (hourlyMinMax.getMin() > cities.get(i).getHourly_wage()){
                    cities.remove(i);
                    i--;
                } else if (hourlyMinMax.getMax() < cities.get(i).getHourly_wage()){
                    cities.remove(i);
                    i--;
                }
            }
        }
        MinMaxInt annualMinMax = cityFilter.getAnnual_wage();
        if (annualMinMax != null){
            for (int i = 0; i < cities.size(); i++){
                if (annualMinMax.getMin() > cities.get(i).getAnnual_wage()){
                    cities.remove(i);
                    i--;
                } else if (annualMinMax.getMax() < cities.get(i).getAnnual_wage()){
                    cities.remove(i);
                    i--;
                }
            }
        }
        MinMaxDbl walkMinMax = cityFilter.getWalkscore();
        if (walkMinMax != null) {
            for (int i = 0; i < cities.size(); i++) {
                if (walkMinMax.getMin() > cities.get(i).getWalkscore()) {
                    cities.remove(i);
                    i--;
                } else if (walkMinMax.getMax() < cities.get(i).getWalkscore()) {
                    cities.remove(i);
                    i--;
                }
            }
        }
        List<String> cityNames = cityNamesFromCities(cities);
        return new ResponseEntity<>(cityNames, HttpStatus.OK);
    }

    @GetMapping(value = "/compare", produces = "application/json")
    public ResponseEntity<?> compareCities(@RequestBody List<String> cityNames){
        List<City> rtn = new ArrayList<>();
        for (String s : cityNames){
            City city = cityService.findByName(s);
            if (city != null){
                rtn.add(city);
            }
        }
        return new ResponseEntity<>(rtn, HttpStatus.OK);
    }

    /* controller below needs refactored into UserController */
    @GetMapping(value = "/favcities")
    public ResponseEntity<?> getFavCity(Authentication authentication)
    {
        User u = userService.findByName(authentication.getName());
        return new ResponseEntity<>(u.getFavcities(), HttpStatus.OK);
    }
}
