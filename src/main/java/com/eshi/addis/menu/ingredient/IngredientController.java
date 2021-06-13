package com.eshi.addis.menu.ingredient;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("ingredients")
@RequiredArgsConstructor
public class IngredientController implements IngredientService{

    private final IngredientServiceImp ingredientService;

    @Override
    public Ingredient store(String branchId, Ingredient ingredient) {
        return ingredientService.store(branchId, ingredient);
    }

    @Override
    public Iterable<Ingredient> store(String branchId, List<@Valid Ingredient> t) {
        return ingredientService.store(branchId,t);
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
        return ingredientService.show(id);
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        return ingredientService.update(id,ingredient);
    }

    @Override
    public boolean delete(long id) {
        return ingredientService.delete(id);
    }

    @Override
    public Iterable<Ingredient> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Ingredient> getAll(String branchId, Pageable pageable) {
        return null;
    }
}
