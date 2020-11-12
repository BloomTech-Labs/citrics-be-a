package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "searches")
public class Search extends Auditable {
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "searches", allowSetters = true)
    private User user;

    @OneToMany(mappedBy = "search", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "search", allowSetters = true)
    private List<CitySearches> cities = new ArrayList<>();

    public Search() {
    }

    public Search(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CitySearches> getCities() {
        return cities;
    }

    public void setCities(List<CitySearches> cities) {
        this.cities = cities;
    }
}
