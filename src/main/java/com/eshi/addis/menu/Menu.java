package com.eshi.addis.menu;

import com.eshi.addis.menu.menuIngredient.MenuIngredient;
import com.eshi.addis.menu.modifier.MenuModifier;
import com.eshi.addis.menu.size.MenuSize;
import com.eshi.addis.order.Status;
import com.eshi.addis.order.StatusConverter;
import com.eshi.addis.restaurant.category.Category;
import com.eshi.addis.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "menus")
public class Menu extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double price;
    @JsonIgnoreProperties(value = {"menu", "hibernateLazyInitializer"})
    @OneToMany(mappedBy = "menu")
    @ToString.Exclude
    private List<MenuSize> sizes;
    private String thumbnailPic;
    @NotNull(message = "Menu name is required")
    @NotBlank(message = "Menu name shouldn't be blank")
    private String name;
    @ManyToOne
    @JsonIgnoreProperties(value = {"menus", "restaurant"})
    private Category category;
    @OneToMany(mappedBy = "menu")
    //@JsonIgnoreProperties(value = {"menu", "pk"})
    @ToString.Exclude
    private List<MenuIngredient> ingredients;
    private boolean available = true;
    @Convert(converter = StatusConverter.class)
    private Status status;
    @OneToMany(mappedBy="menu")
    @ToString.Exclude
    private List<MenuModifier> menuModifiers;

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Menu menu = (Menu) o;
//
//        return Objects.equals(id, menu.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return 1525026887;
//    }
}
