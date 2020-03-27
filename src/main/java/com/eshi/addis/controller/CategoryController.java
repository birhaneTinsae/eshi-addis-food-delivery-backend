package com.eshi.addis.controller;

import com.eshi.addis.dto.MenuCategoryDto;
import com.eshi.addis.model.Category;
import com.eshi.addis.model.Restaurant;
import com.eshi.addis.service.CategoryService;
import com.eshi.addis.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
public class CategoryController {
    private CategoryService categoryService;
    private RestaurantService restaurantService;

    public CategoryController(CategoryService categoryService, RestaurantService restaurantService) {
        this.categoryService = categoryService;
        this.restaurantService = restaurantService;
    }

    @PostMapping("/service-providers/{id}")
    public Category create(@PathVariable long id, @RequestBody MenuCategoryDto menuCategoryDto) {
        Restaurant restaurant = restaurantService.getServiceProvider(id);
        Category category = new Category();
        category.setTitle(menuCategoryDto.getTitle());
        category.setRestaurant(restaurant);


        if (menuCategoryDto.getMenus() != null && !menuCategoryDto.getMenus().isEmpty()) {
            category.setMenus(menuCategoryDto.getMenus());
        }
        return categoryService.create(category);
    }
}
