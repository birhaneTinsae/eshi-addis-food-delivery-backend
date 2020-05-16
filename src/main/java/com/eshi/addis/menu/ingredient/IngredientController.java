package com.eshi.addis.menu.ingredient;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ingredients")
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/service-provider/{id}")
    public Ingredient create(@PathVariable long id, @RequestBody Ingredient ingredient) {
        return ingredientService.create(id, ingredient);
    }
}
