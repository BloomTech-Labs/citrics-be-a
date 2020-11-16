package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.City;

import java.util.List;

//import com.lambdaschool.foundation.models.CityIdName;

public interface CityService
{
    /**
     * Find all cities
     * @return list of cities
     */
    List<City> findAll();

    /**
     * Finds city by cityid
     * @param id cityid
     * @return City matching id
     */
    City findCityById(long id);

    /**
     * Saves new city
     * @param city new city to be saved
     * @return newly saved City
     */
    City save(City city);

    /**
     * Saves new city from DS API schema
     * @param city new city to be saved
     * @return newly saved city
     */
//    City saveDs(DSCity city) throws Exception;

    /**
     * Finds city by citynamestate field
     * @param name citystatename
     * @return City object matching name
     */
    City findByName(String name);

    /**
     * Finds all cities id's and citynamestate's
     * @return List of city id's and citynamestates
     */
//    List<CityIdName> findAllIds();

    /**
     * Finds the average value for all fields in cities excluding the historical data
     * @return a City object with the average of all city fields in the DB
     */
    City findAverageCity();

    /**
     * Finds the average city object stored in DB
     * @return
     */
    City returnAverageCity();
}
