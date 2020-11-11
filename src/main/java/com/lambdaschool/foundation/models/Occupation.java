package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "occupations")
public class Occupation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long occid;

    private String occ_title;

    private int annual_wage;

    private double hourly_wage;

    private double jobs_1000;

    private double loc_quotient;

    @OneToMany(mappedBy = "occupation",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "occupation",allowSetters = true)
    private List<CityOccs> cities = new ArrayList<>();


    public Occupation() {
    }

    public Occupation(String occ_title, double hourly_wage, int annual_wage, double jobs_1000, double loc_quotient) {
        this.occ_title = occ_title;
        this.hourly_wage = hourly_wage;
        this.annual_wage = annual_wage;
        this.jobs_1000 = jobs_1000;
        this.loc_quotient = loc_quotient;
    }

    public long getOccid() {
        return occid;
    }

    public void setOccid(long occid) {
        this.occid = occid;
    }

    public String getOcc_title() {
        return occ_title;
    }

    public void setOcc_title(String occ_title) {
        this.occ_title = occ_title;
    }

    public int getAnnual_wage() {
        return annual_wage;
    }

    public void setAnnual_wage(int annual_wage) {
        this.annual_wage = annual_wage;
    }

    public double getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(double hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    public double getJobs_1000() {
        return jobs_1000;
    }

    public void setJobs_1000(double jobs_1000) {
        this.jobs_1000 = jobs_1000;
    }

    public double getLoc_quotient() {
        return loc_quotient;
    }

    public void setLoc_quotient(double loc_quotient) {
        this.loc_quotient = loc_quotient;
    }

    public List<CityOccs> getCities() {
        return cities;
    }

    public void setCities(List<CityOccs> cities) {
        this.cities = cities;
    }
}
