package com.eshi.addis.menu.menuIngredient;

import com.eshi.addis.menu.Menu;
import com.eshi.addis.menu.ingredient.Ingredient;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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

    public MenuIngredient(Menu menu, Ingredient ingredient, boolean required) {
        this.pk = new MenuIngredientKey(menu.getId(), ingredient.getId());
        this.menu = menu;
        this.ingredient = ingredient;
        this.required = required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MenuIngredient that = (MenuIngredient) o;
        return pk != null && Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk);
    }
}
