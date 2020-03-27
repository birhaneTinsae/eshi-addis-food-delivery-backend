package com.eshi.addis.dto;

import com.eshi.addis.model.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class MenuDto implements Serializable {
    private Menu menu;
    private Set<IngredientDto> ingredients;
}
