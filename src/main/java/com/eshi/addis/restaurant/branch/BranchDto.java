package com.eshi.addis.restaurant.branch;

import com.eshi.addis.menu.Menu;
import com.eshi.addis.restaurant.RestaurantDto;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BranchDto implements Serializable {
    private long id;
    private String title;
    private List<Menu> menus;
    private RestaurantDto restaurant;
}
