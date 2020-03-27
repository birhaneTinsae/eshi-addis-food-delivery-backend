package com.eshi.addis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean available;
    @JsonIgnore
    @OneToMany(mappedBy = "ingredient")
    private List<MenuIngredient> menuIngredients;
    @ManyToOne
    @JsonIgnoreProperties(value = {"ingredients"})
    private Restaurant restaurant;
}
