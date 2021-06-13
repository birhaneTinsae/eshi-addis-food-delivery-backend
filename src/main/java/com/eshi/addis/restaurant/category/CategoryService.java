package com.eshi.addis.restaurant.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Category createCategory(String restaurantId, Category category);

    Category getCategory(long categoryId);

    Category updateCategory(long categoryId, Category category);

    void deleteCategory(long categoryId);

    Page<Category> getCategories(Pageable pageable);

    Page<Category> getRestaurantCategories(String restaurantId, Pageable pageable);
}
