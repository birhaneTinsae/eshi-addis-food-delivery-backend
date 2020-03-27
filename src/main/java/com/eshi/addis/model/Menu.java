package com.eshi.addis.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "menu")
    private List<MenuSize> sizes;
    private String thumbnailPic;
    private String name;
    @ManyToOne
    @JsonIgnoreProperties(value = {"menus","hibernateLazyInitializer"})
    private Category category;

    @OneToMany(mappedBy = "menu")
    @JsonIgnoreProperties(value = {"menu"})
    private List<MenuIngredient> ingredients;
    private boolean available = true;
}
