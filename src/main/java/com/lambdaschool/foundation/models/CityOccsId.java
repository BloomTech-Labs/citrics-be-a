package com.lambdaschool.foundation.models;

import java.io.Serializable;

public class CityOccsId implements Serializable {

    private long city;

    private long occupation;


    public CityOccsId() {
    }

    public CityOccsId(long city, long occupation) {
        this.city = city;
        this.occupation = occupation;
    }


    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }

    public long getOccs() {
        return occupation;
    }

    public void setOccs(long occs) {
        this.occupation = occs;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        CityOccsId that = (CityOccsId) o;
        return city == that.city &&
                occupation == that.occupation;
    }

    @Override
    public int hashCode() {
        return 37;
    }


}
