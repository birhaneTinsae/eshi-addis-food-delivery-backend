package com.eshi.addis.menu.menuIngredient;

import com.eshi.addis.menu.Menu;
import com.eshi.addis.menu.ingredient.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuIngredient implements Serializable {
    @EmbeddedId
    private MenuIngredientKey pk;

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    private boolean required;

  
}
