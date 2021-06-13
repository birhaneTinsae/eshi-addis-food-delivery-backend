package com.eshi.addis.menu.ingredient;

import com.eshi.addis.utils.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IngredientService extends Common<Ingredient, Ingredient> {
    @GetMapping("{branchId}")
    Page<Ingredient> getAll(@PathVariable() String branchId, Pageable pageable);

    @PostMapping("{branchId}")
    Ingredient store(@PathVariable() String branchId, @RequestBody Ingredient ingredient);

    @PostMapping("list/{branchId}")
    Iterable<Ingredient> store(@PathVariable() String branchId, @RequestBody List<@Valid Ingredient> t);


}
