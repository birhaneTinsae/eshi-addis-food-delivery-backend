package com.eshi.addis.menu.ingredient;

import com.eshi.addis.restaurant.RestaurantService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class IngredientService {
    private IngredientRepository ingredientRepository;
    private RestaurantService restaurantService;

    public IngredientService(IngredientRepository ingredientRepository, RestaurantService restaurantService) {
        this.ingredientRepository = ingredientRepository;
        this.restaurantService = restaurantService;
    }

    public Ingredient create(long id, Ingredient ingredient) {
        ingredient.setRestaurant(restaurantService.getServiceProvider(id));
        return ingredientRepository.save(ingredient);
    }

    public  Ingredient getById(long id){
        return ingredientRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }
}
