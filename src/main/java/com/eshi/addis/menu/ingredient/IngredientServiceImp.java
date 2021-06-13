package com.eshi.addis.menu.ingredient;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

import static com.eshi.addis.utils.Util.getNullPropertyNames;


@Service
@RequiredArgsConstructor
public class IngredientServiceImp implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final RestaurantService restaurantService;

    @Override
    public Page<Ingredient> getAll(String branchId, Pageable pageable) {
        return ingredientRepository.findAllByRestaurant_Id(branchId,pageable);
    }

    @Override
    public Ingredient store(String branchId, Ingredient ingredient) {
        var restaurant = restaurantService.getRestaurant(branchId);
        ingredient.setRestaurant(restaurant);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Iterable<Ingredient> store(String branchId, List<@Valid Ingredient> t) {
        var restaurant = restaurantService.getRestaurant(branchId);
        t.forEach(i -> i.setRestaurant(restaurant));
        return ingredientRepository.saveAll(t);
    }

    @Override
    public Ingredient store(Ingredient ingredient) {
        return null;
    }

    @Override
    public Iterable<Ingredient> store(List<@Valid Ingredient> t) {
        return null;
    }

    @Override
    public Ingredient show(long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Ingredient.class, "Id", String.valueOf(id)));

    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        Ingredient i = show(id);
        BeanUtils.copyProperties(ingredient, i, getNullPropertyNames(ingredient));
        return ingredientRepository.save(i);
    }

    @Override
    public boolean delete(long id) {
        ingredientRepository.deleteById(id);
        return !ingredientRepository.existsById(id);
    }

    @Override
    public Iterable<Ingredient> getAll(Pageable pageable) {
        return null;
    }


}
