package com.eshi.addis.dto;

import com.eshi.addis.model.Ingredient;
import lombok.Data;

import java.io.Serializable;

@Data
public class IngredientDto implements Serializable {
    private Ingredient ingredient;
    private boolean required=true;
}
