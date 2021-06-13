package com.eshi.addis.dto;

import com.eshi.addis.menu.ingredient.Ingredient;
import lombok.Data;

import java.io.Serializable;

@Data
public class IngredientDTO implements Serializable {
    private Ingredient ingredient;
    private boolean required=true;
}
