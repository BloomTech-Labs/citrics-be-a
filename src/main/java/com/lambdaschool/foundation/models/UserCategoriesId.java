package com.lambdaschool.foundation.models;

import java.io.Serializable;

public class UserCategoriesId implements Serializable {

    private long user;

    private long categories;

    public UserCategoriesId() {
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getCategories() {
        return categories;
    }

    public void setCategories(long categories) {
        this.categories = categories;
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
        UserCategoriesId that = (UserCategoriesId) o;
        return user == that.user &&
                categories == that.categories;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
