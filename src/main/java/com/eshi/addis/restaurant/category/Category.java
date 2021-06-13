package com.eshi.addis.restaurant.category;

import com.eshi.addis.menu.Menu;
import com.eshi.addis.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "menu_categories")
@EqualsAndHashCode
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties(value = "category")
    private List<Menu> menus;
    @ManyToOne
    @JsonIgnore// @JsonIgnoreProperties(value = {"categories"})
    private Restaurant restaurant;
}
