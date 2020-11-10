package com.lambdaschool.foundation.models;

import java.io.Serializable;

public class UserCategoriesId implements Serializable {

    private long user;

    private long category;

    public UserCategoriesId() {
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getCategory() {
        return category;
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
                category == that.category;
    }

    @Override
    public int hashCode() {
        return 37;
    }
}
