package com.lambdaschool.foundation.models;

public class CityFilter {
    private MinMaxInt population;
    private MinMaxInt studio;
    private MinMaxInt onebr;
    private MinMaxInt twobr;
    private MinMaxInt threebr;
    private MinMaxInt fourbr;
    private MinMaxDbl hourly_wage;
    private MinMaxInt annual_wage;
    private MinMaxDbl walkscore;

    public CityFilter() {
    }

    public CityFilter(
            MinMaxInt population,
            MinMaxInt studio,
            MinMaxInt onebr,
            MinMaxInt twobr,
            MinMaxInt threebr,
            MinMaxInt fourbr,
            MinMaxDbl hourly_wage,
            MinMaxInt annual_wage,
            MinMaxDbl walkscore) {
        this.population = population;
        this.studio = studio;
        this.onebr = onebr;
        this.twobr = twobr;
        this.threebr = threebr;
        this.fourbr = fourbr;
        this.hourly_wage = hourly_wage;
        this.annual_wage = annual_wage;
        this.walkscore = walkscore;
    }

    public MinMaxInt getPopulation() {
        return population;
    }

    public void setPopulation(MinMaxInt population) {
        this.population = population;
    }

    public MinMaxInt getStudio() {
        return studio;
    }

    public void setStudio(MinMaxInt studio) {
        this.studio = studio;
    }

    public MinMaxInt getOnebr() {
        return onebr;
    }

    public void setOnebr(MinMaxInt onebr) {
        this.onebr = onebr;
    }

    public MinMaxInt getTwobr() {
        return twobr;
    }

    public void setTwobr(MinMaxInt twobr) {
        this.twobr = twobr;
    }

    public MinMaxInt getThreebr() {
        return threebr;
    }

    public void setThreebr(MinMaxInt threebr) {
        this.threebr = threebr;
    }

    public MinMaxInt getFourbr() {
        return fourbr;
    }

    public void setFourbr(MinMaxInt fourbr) {
        this.fourbr = fourbr;
    }

    public MinMaxDbl getHourly_wage() {
        return hourly_wage;
    }

    public void setHourly_wage(MinMaxDbl hourly_wage) {
        this.hourly_wage = hourly_wage;
    }

    public MinMaxInt getAnnual_wage() {
        return annual_wage;
    }

    public void setAnnual_wage(MinMaxInt annual_wage) {
        this.annual_wage = annual_wage;
    }

    public MinMaxDbl getWalkscore() {
        return walkscore;
    }

    public void setWalkscore(MinMaxDbl walkscore) {
        this.walkscore = walkscore;
    }
}
