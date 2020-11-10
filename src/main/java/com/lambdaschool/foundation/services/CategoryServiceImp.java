package com.lambdaschool.foundation.services;

import com.lambdaschool.foundation.models.Category;
import com.lambdaschool.foundation.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "categoryService")
public class CategoryServiceImp implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category save(Category category) {
       return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category, long id) {
        return null;
    }

    @Override
    public Category findCategoryById(long id) {
        return null;
    }
}
