package com.eshi.addis.dto;

import com.eshi.addis.menu.Menu;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryDTO implements Serializable {
    private long id;
    private String title;
    private List<Menu> menus;
    private RestaurantDTO restaurant;
}
