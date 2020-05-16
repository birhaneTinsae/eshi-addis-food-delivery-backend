package com.eshi.addis.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Menu> menus = new ArrayList<>();
    @ManyToOne
   @JsonIgnore// @JsonIgnoreProperties(value = {"categories"})
    private Restaurant restaurant;
}
