package com.eshi.addis.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    @JsonIgnoreProperties(value = {"menu", "hibernateLazyInitializer"})
    @OneToMany(mappedBy = "menu")
    private List<MenuSize> sizes;
    private String thumbnailPic;
    @NotNull(message = "Menu name is required")
    @NotBlank(message = "Menu name shouldn't be blank")
    private String name;
    @ManyToOne
    @JsonIgnoreProperties(value = {"menus", "restaurant"})
    private Category category;
  //  @JsonIgnore
    @OneToMany(mappedBy = "menu")
    @JsonIgnoreProperties(value = {"menu","pk"})
    private List<MenuIngredient> ingredients;
    private boolean available = true;
}
