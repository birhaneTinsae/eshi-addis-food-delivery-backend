package com.eshi.addis.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class MenuIngredientKey implements Serializable {
    @Column(name = "menu_id")
    Long menuId;
    @Column(name = "ingredient_id")
    Long ingredientId;
}
