package com.eshi.addis.menu.menuIngredient;

import com.eshi.addis.menu.Menu;
import com.eshi.addis.menu.ingredient.Ingredient;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MenuIngredient {

    @EmbeddedId
    private MenuIngredientKey pk;


    @ManyToOne
    @MapsId("menu_id")
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @MapsId("ingredient_id")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private boolean required;

  
}
