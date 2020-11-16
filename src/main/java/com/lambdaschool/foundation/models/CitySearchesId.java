package com.lambdaschool.foundation.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CitySearchesId implements Serializable {
    public long search;

    public long city;

    public CitySearchesId() {
    }

    public long getSearch() {
        return search;
    }

    public void setSearch(long search) {
        this.search = search;
    }

    public long getCity() {
        return city;
    }

    public void setCity(long city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CitySearchesId that = (CitySearchesId) o;
        return search == that.search && city == that.city;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
