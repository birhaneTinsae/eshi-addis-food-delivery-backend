package com.eshi.addis.controller;

import com.eshi.addis.dto.IngredientDto;
import com.eshi.addis.dto.MenuDto;
import com.eshi.addis.model.*;
import com.eshi.addis.service.CategoryService;
import com.eshi.addis.service.MenuService;
import com.eshi.addis.service.MenuSizeService;
import com.eshi.addis.service.RestaurantService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("menus")
public class MenuController {
    private MenuService menuService;
    private RestaurantService restaurantService;
    private CategoryService categoryService;
    private MenuSizeService menuSizeService;

    public MenuController(MenuService menuService, RestaurantService restaurantService, CategoryService categoryService, MenuSizeService menuSizeService) {
        this.menuService = menuService;
        this.restaurantService = restaurantService;
        this.categoryService = categoryService;
        this.menuSizeService = menuSizeService;
    }

    @PostMapping("/service-provider/{id}/category/{categoryId}")
    @Transactional
    public Menu create(@PathVariable long id, @PathVariable long categoryId, @RequestBody MenuDto menuDto) {
        Category category = categoryService.getCategory(categoryId);
//        ServiceProvider serviceProvider=serviceProviderService.getServiceProvider(id);
        Menu menu = menuDto.getMenu();
        menu.setCategory(category);
//        menu.setServiceProvider(serviceProvider);

        if (menuDto.getIngredients() != null && !menuDto.getIngredients().isEmpty()) {
            List<MenuIngredient> ingredients=new ArrayList<>();
            for (IngredientDto ingredient : menuDto.getIngredients()) {
                MenuIngredient menuIngredient = new MenuIngredient();
                menuIngredient.setMenu(menu);
                menuIngredient.setRequired(ingredient.isRequired());
                ingredients.add(menuIngredient);
            }

          menu.setIngredients(menuService.createMenuIngredient(ingredients));
        }
        if (menuDto.getMenu().getSizes() != null && !menuDto.getMenu().getSizes().isEmpty()) {

            menu.setSizes(menuSizeService.create(menuDto.getMenu().getSizes()));

        }

        menuService.create(menu);

        return menu;
    }

    @PutMapping("/{id}")
    public Menu update(@PathVariable long id, @RequestBody Menu menu) {
        return null;
    }

    @GetMapping("/service-provider/{id}")
    public Iterable<Category> getServiceProviderMenuCategories(@PathVariable long id) {
        return restaurantService.getServiceProviderMenuCategories(id);
    }


}
