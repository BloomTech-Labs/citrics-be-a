package com.lambdaschool.foundation.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cities")
public class City extends Auditable
{

    /**
     * Generate City id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cityid;

    private String state;

    private int studio;

    private int onebr;

    private int twobr;

    private int threebr;

    private int fourbr;

    private String occ_title;

    private Double hourly_wage;

    private int annual_wage;

    private String climate_zone;

    private String simple_climate;

    private float walkscore;

    private int population;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "city", allowSetters = true)
    private List<CityOccs> occupations = new ArrayList<>();

    /**
     * List of User's who have favorited the city
     */
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<UserCities> users = new HashSet<>();

    /**
     * The Default constructor needed by JPA
     */
    public City()
    {
    }

    public long getCityid() {
        return cityid;
    }

    public void setCityid(long cityid) {
        this.cityid = cityid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getStudio() {
        return studio;
    }

    public void setStudio(int studio) {
        this.studio = studio;
    }

    public int getOnebr() {
        return onebr;
    }

    public void setOnebr(int onebr) {
        this.onebr = onebr;
    }

    public int getTwobr() {
        return twobr;
    }

    public void setTwobr(int twobr) {
        this.twobr = twobr;
    }

    public int getThreebr() {
        return threebr;
    }

    public void setThreebr(int threebr) {
        this.threebr = threebr;
    }

    public int getFourbr() {
        return fourbr;
    }

    public void setFourbr(int fourbr) {
        this.fourbr = fourbr;
    }

    public String getOcc_title() {
        return occ_title;
    }

    public void setOcc_title(String occ_title) {
        this.occ_title = occ_title;
    }

    public Double getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(Double hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    public int getAnnual_wage() {
        return annual_wage;
    }

    public void setAnnual_wage(int annual_wage) {
        this.annual_wage = annual_wage;
    }

    public String getClimate_zone() {
        return climate_zone;
    }

    public void setClimate_zone(String climate_zone) {
        this.climate_zone = climate_zone;
    }

    public String getSimple_climate() {
        return simple_climate;
    }

    public void setSimple_climate(String simple_climate) {
        this.simple_climate = simple_climate;
    }

    public Set<UserCities> getUsers() {
        return users;
    }

    public void setUsers(Set<UserCities> users) {
        this.users = users;
    }

    public List<CityOccs> getOccupations() {
        return occupations;
    }

    public void setOccupations(List<CityOccs> occupations) {
        this.occupations = occupations;
    }

    public float getWalkscore() {
        return walkscore;
    }

    public void setWalkscore(float walkscore) {
        this.walkscore = walkscore;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

}
