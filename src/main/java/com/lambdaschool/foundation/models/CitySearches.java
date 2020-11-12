package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "citysearches")
@IdClass(CitySearchesId.class)
public class CitySearches extends Auditable implements Serializable {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "searchid")
    @JsonIgnoreProperties(value = "cities", allowSetters = true)
    private Search search;

    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "cityid")
    @JsonIgnoreProperties(value = "searches", allowSetters = true)
    private City city;

    public CitySearches() {
    }

    public CitySearches(@NotNull Search search, @NotNull City city) {
        this.search = search;
        this.city = city;
    }

    public Search getSearch() {
        return search;
    }

    public void setSearch(Search search) {
        this.search = search;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof CitySearches)) return false;

        CitySearches that = (CitySearches) o;

        return ((search == null) ? 0 : search.getSearchid()) == ((that.search == null) ? 0 : that.search.getSearchid()) &&
                ((city == null) ? 0 : city.getCityid()) == ((that.city == null) ? 0 : that.city.getCityid());
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
