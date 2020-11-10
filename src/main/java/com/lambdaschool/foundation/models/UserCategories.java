//package com.lambdaschool.foundation.models;
//
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.io.Serializable;
//
//@Entity
//@Table(name = "usercategories")
//@IdClass(UserCategoriesId.class)
//public class UserCategories extends Auditable implements Serializable {
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "userid")
//    @JsonIgnoreProperties(value = "categories",allowSetters = true)
//    private User user;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "catid")
//    @JsonIgnoreProperties(value = "users",allowSetters = true)
//    private Category category;
//
//
//    public UserCategories() {
//    }
//
//    public UserCategories(User user, Category category) {
//        this.user = user;
//        this.category = category;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//    @Override
//    public int hashCode() {
//        return 37;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//        {
//            return true;
//        }
//        if (!(o instanceof UserCategories))
//        {
//            return false;
//        }
//        UserCategories that = (UserCategories) o;
//        return ((user == null) ? 0 : user.getUserid()) == ((that.user == null) ? 0 : that.user.getUserid()) &&
//                ((category == null) ? 0 : category.getCatid()) == ((that.category == null) ? 0 : that.category.getCatid());
//    }
//}
