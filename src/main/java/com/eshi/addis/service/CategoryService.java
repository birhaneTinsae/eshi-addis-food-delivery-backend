package com.eshi.addis.service;

import com.eshi.addis.model.Category;
import com.eshi.addis.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategory(long categoryId) {
        return categoryRepository.getOne(categoryId);
    }
}
