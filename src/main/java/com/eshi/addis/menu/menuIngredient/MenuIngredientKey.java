package com.eshi.addis.menu.menuIngredient;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class MenuIngredientKey implements Serializable {
    @Column(name = "menu_id")
    private Long menuId;
    @Column(name = "ingredient_id")
    private Long ingredientId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MenuIngredientKey that = (MenuIngredientKey) o;

        if (!Objects.equals(menuId, that.menuId)) return false;
        return Objects.equals(ingredientId, that.ingredientId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(menuId);
        result = 31 * result + (Objects.hashCode(ingredientId));
        return result;
    }
}
