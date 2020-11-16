package com.lambdaschool.foundation.services;


import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.City;
import com.lambdaschool.foundation.models.User;
import com.lambdaschool.foundation.models.UserCities;
import com.lambdaschool.foundation.repository.CityRepository;
import com.lambdaschool.foundation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "cityService")
public class CityServiceImpl implements CityService
{
    /**
     * Connections to needed repositories
     */
    @Autowired
    private CityRepository cityrepo;

    @Autowired
    private UserRepository userrepo;


    /**
     * Find all cities in DB
     *
     * @return list of Cities
     */
    @Override
    public List<City> findAll()
    {
        List<City> list = new ArrayList<>();

        cityrepo.findAll()
            .iterator()
            .forEachRemaining(list::add);

        return list;
    }

    /**
     * find city by cityid
     *
     * @param id local id of city
     * @return City object matching the city id or
     * @throws ResourceNotFoundException
     */
    @Override
    public City findCityById(long id) throws ResourceNotFoundException
    {
        return cityrepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("City id " + id + " not found!"));
    }

    /**
     * Saves new city to DB
     * Had to modify last minute to accept new city schema returned by DS
     * @param city new city to be saved
     * @return newly saved city
     */
    @Transactional
    @Override
    public City save(City city)
    {
        City c = new City();

        if (city.getCityid() != 0)
        {
            cityrepo.findById(city.getCityid())
                .orElseThrow(() -> new ResourceNotFoundException("City id " + city.getCityid() + " not found!"));
            c.setCityid(city.getCityid());
        }


        for (UserCities user : city.getUsers())
        {
            c.getUsers()
                .add(user);

        }

        return cityrepo.save(city);
    }

    @Override
    public City findByName(String name) {
        City city = cityrepo.findByName(name);
        if (city == null) {
            throw new ResourceNotFoundException("city by name of " + name + " not found!");
        }
        return city;
    }

    /**
     * Saves new city from DS API schema
     * Had to modify last minute to accept new city schema returned by DS
     * @param city JSON City to be saved
     * @return newly saved City object
     */

    /**
     * Find city by citynamestate
     *
     * @param name citynamestate
     * @return city object match name or throws exception
     */
//    @Override
//    public City findByCName(String name)
//    {
//        City c = cityrepo.findByCitynamestate(name);
//        if (c == null)
//        {
//            throw new ResourceNotFoundException("City name " + name + " not found!");
//        }
//        return c;
//    }

    /**
     * Find all cities and their name's + id's
     *
     * @return List of City name's and Id's
     */
//    @Override
//    public List<CityIdName> findAllIds()
//    {
//        List<CityIdName> cities = new ArrayList<>();
//
//        cityrepo.findAll()
//            .iterator()
//            .forEachRemaining((city) -> cities.add(new CityIdName(city.getCityid(),
//                city.getState())));
//
//        return cities;
//    }

    /**
     * Find the average value for all city fields
     * excludes historical data
     * @return a City with the field averages of all cities
     */
    @Override
    public City findAverageCity()
    {
        List<City> cities = new ArrayList<>();

        City c = new City();
        int totalCities = 0;
        String cityNameState = "National Average, USA";
        double totalLatitude = 0;
        double totalLongitude = 0;
        double totalPopulation = 0;
        double totalDensityMiSq = 0;
        double totalDensityKmSq = 0;
        double totalAge = 0;
        double totalHousehold = 0;
        double totalIndividual = 0;
        double totalHousing = 0;
        double totalRent = 0;
        double costOfLivingIndex = 0;
        double totalTemp = 0;
        double totalPerc = 0;
        double totalCov = 0;

        cityrepo.findAll()
            .iterator()
            .forEachRemaining(cities::add);



        return c;
    }


    /**
     * Finds the average city object stored in DB
     * @return National Average City
     */
    @Override
    public City returnAverageCity()
    {
        return findByName("average");
    }
}
