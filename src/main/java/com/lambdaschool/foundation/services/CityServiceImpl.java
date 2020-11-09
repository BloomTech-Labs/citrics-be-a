package com.lambdaschool.foundation.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.foundation.exceptions.ResourceNotFoundException;
import com.lambdaschool.foundation.models.*;
import com.lambdaschool.foundation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

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

        c.setCitynamestate(city.getCitynamestate());
        c.setStatecode(city.getStatecode());
        c.setTimezone(city.getTimezone());
        c.setLatitude(city.getLatitude());
        c.setLogitude(city.getLogitude());
        c.setFpis(city.getFpis());
        c.setGnis(city.getGnis());
        c.setWikiimgurl(city.getWikiimgurl());
        c.setWebsite(city.getWebsite());
        c.setPopulation(city.getPopulation());
        c.setDensitymisq(city.getDensitymisq());
        c.setDensitykmsq(city.getDensitykmsq());
        c.setAverageage(city.getAverageage());
        c.setHouseholdincome(city.getHouseholdincome());
        c.setIndividualincome(city.getIndividualincome());
        c.setAveragehouse(city.getAveragehouse());
        c.setRent(city.getRent());
        c.setCostoflivingindex(city.getCostoflivingindex());
        c.setAcastatus(city.getAcastatus());





        for (UserCities user : city.getUsers())
        {
            c.getUsers()
                .add(user);

        }

        c.setAveragetemp(city.getAveragetemp());
        c.setAverageperc(city.getAverageperc());
        c.setAvgnewcovidcases(city.getAvgnewcovidcases());

        return cityrepo.save(c);
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
    @Override
    public City findByCName(String name)
    {
        City c = cityrepo.findByCitynamestate(name);
        if (c == null)
        {
            throw new ResourceNotFoundException("City name " + name + " not found!");
        }
        return c;
    }

    /**
     * Find all cities and their name's + id's
     *
     * @return List of City name's and Id's
     */
    @Override
    public List<CityIdName> findAllIds()
    {
        List<CityIdName> cities = new ArrayList<>();

        cityrepo.findAll()
            .iterator()
            .forEachRemaining((city) -> cities.add(new CityIdName(city.getCityid(),
                city.getCitynamestate())));

        return cities;
    }

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

        for (int i = 0; i < cities.size(); i++)
        {
            totalCities++;
            City x = cities.get(i);
            totalLatitude += x.getLatitude();
            totalLongitude += x.getLogitude();
            totalPopulation += x.getPopulation();
            totalDensityMiSq += (c.getDensitymisq() != null) ? x.getDensitymisq() : 0;
            totalDensityKmSq += (c.getDensitykmsq() != null) ? x.getDensitykmsq() : 0;
            totalAge += x.getAverageage();
            totalHousehold += x.getHouseholdincome();
            totalIndividual += x.getIndividualincome();
            totalHousing += x.getAveragehouse();
            totalRent += x.getRent();
            totalTemp += x.getAveragetemp();
            totalPerc += x.getAverageperc();
            totalCov += x.getAvgnewcovidcases();

            costOfLivingIndex += (x.getCostoflivingindex() != null) ? x.getCostoflivingindex() : 0;
        }

        c.setCitynamestate(cityNameState);
        c.setLatitude(totalLatitude / totalCities);
        c.setLogitude(totalLongitude / totalCities);
        c.setPopulation(totalPopulation / totalCities);
        c.setDensitymisq(totalDensityMiSq / totalCities);
        c.setDensitykmsq(totalDensityKmSq / totalCities);
        c.setAverageage(totalAge / totalCities);
        c.setHouseholdincome(totalHousehold / totalCities);
        c.setIndividualincome(totalIndividual / totalCities);
        c.setAveragehouse(totalHousing / totalCities);
        c.setRent(totalRent / totalCities);
        c.setCostoflivingindex(costOfLivingIndex / totalCities);
        c.setAveragetemp(totalTemp / totalCities);
        c.setAverageperc(totalPerc / totalCities);
        c.setAvgnewcovidcases(totalCov / totalCities);

        return c;
    }


    /**
     * Finds the average city object stored in DB
     * @return National Average City
     */
    @Override
    public City returnAverageCity()
    {
        return findByCName("National Average, USA");
    }

    /**
     * Saves the city by id to current users fav cities
     * @param id cityid of city to be saved
     * @param user user extracted by controller
     */
    @Override
    public void saveFavCity(long id, User user)
    {
        City c = findCityById(id);
        UserCities us = new UserCities(user, c);

        user.getFavcities().add(us);
        c.getUsers().add(us);
    }
}
