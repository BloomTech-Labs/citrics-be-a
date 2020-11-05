package com.lambdaschool.foundation.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name ="category")
public class Category extends Auditable {


    @Id
    @GeneratedValue
    private long catid;

    @Column(nullable = false)
    private String cat;

    @Column(nullable = false)
    private long pointerindex;


//Many to one with users
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "category",allowSetters = true)
    private Set<UserCategories> users = new HashSet<>();


    public Category() {
    }

    public Category(long catid, String cat, long pointerindex) {
        this.catid = catid;
        this.cat = cat;
        this.pointerindex = pointerindex;
    }


    public long getCatid() {
        return catid;
    }

    public void setCatid(long catid) {
        this.catid = catid;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public long getPointerindex() {
        return pointerindex;
    }

    public void setPointerindex(long pointerindex) {
        this.pointerindex = pointerindex;
    }

    @Override
    public String toString() {
        return "Category{" +
                "catid=" + catid +
                ", cat='" + cat + '\'' +
                ", pointerindex=" + pointerindex +
                '}';
    }
}
