package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.UserCategories;
import com.lambdaschool.foundation.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserCategoryServiceImp implements UserCategoryService{

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    public List<UserCategories> findAll() {
        List<UserCategories> list = new ArrayList<>();
        userCategoryRepository.findAll().iterator()
                .forEachRemaining(list::add);

        return list;
    }

    @Override
    public UserCategories update(UserCategories categories, long catid) {
        return null;
    }


}
