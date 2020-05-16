package com.eshi.addis.restaurant.category;

import com.eshi.addis.dto.MenuCategoryDto;
import com.eshi.addis.restaurant.Restaurant;
import com.eshi.addis.restaurant.RestaurantService;
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
