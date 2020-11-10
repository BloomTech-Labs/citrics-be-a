package com.lambdaschool.foundation.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@IdClass(CityOccsId.class)
@Table(name = "cityoccupations")
public class CityOccs extends Auditable implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "cityid")
    @JsonIgnoreProperties(value = "occupations",allowSetters = true)
    private City city;

    @Id
    @ManyToOne
    @JoinColumn(name = "occid")
    @JsonIgnoreProperties(value = "cities",allowSetters = true)
    private Occupation occupation;

    public CityOccs() {
    }

    public CityOccs(City city, Occupation occupation) {
        this.city = city;
        this.occupation = occupation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }


}
