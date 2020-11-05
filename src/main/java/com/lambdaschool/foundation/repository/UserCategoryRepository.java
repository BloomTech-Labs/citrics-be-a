package com.lambdaschool.foundation.repository;

import com.lambdaschool.foundation.models.UserCategories;
import org.springframework.data.repository.CrudRepository;

public interface UserCategoryRepository extends CrudRepository<UserCategories,Long> {

    UserCategories save(UserCategories userCategories);
}
