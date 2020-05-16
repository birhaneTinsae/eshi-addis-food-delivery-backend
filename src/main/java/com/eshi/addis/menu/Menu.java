package com.eshi.addis.menu;

import com.eshi.addis.menu.menuIngredient.MenuIngredient;
import com.eshi.addis.menu.size.MenuSize;
import com.eshi.addis.order.Status;
import com.eshi.addis.restaurant.category.Category;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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
    private Status status;


}
