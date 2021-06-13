package com.eshi.addis.menu.ingredient;

import com.eshi.addis.menu.menuIngredient.MenuIngredient;
import com.eshi.addis.restaurant.Restaurant;
import com.eshi.addis.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity(name = "ingredients")
public class Ingredient extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Ingredient name is required")
    @NotBlank(message = "Ingredient name shouldn't be blank")
    private String name;
    private boolean available;
    @JsonIgnore
    @OneToMany(mappedBy = "ingredient")
    private List<MenuIngredient> menuIngredients;
    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;
}
