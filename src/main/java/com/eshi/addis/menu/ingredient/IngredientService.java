package com.eshi.addis.menu.ingredient;

import com.eshi.addis.utils.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface IngredientService extends Common<Ingredient, Ingredient> {
    @GetMapping("{branchId}")
    Page<Ingredient> getAll(@PathVariable() String branchId, Pageable pageable);

    @PostMapping("{branchId}")
    @ResponseStatus(HttpStatus.CREATED)
    Ingredient store(@PathVariable() String branchId, @RequestBody Ingredient ingredient);

    @PostMapping("list/{branchId}")
    Iterable<Ingredient> store(@PathVariable() String branchId, @RequestBody List<@Valid Ingredient> t);


}
