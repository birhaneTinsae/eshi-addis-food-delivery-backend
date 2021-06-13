package com.eshi.addis.dto;

import com.eshi.addis.menu.Menu;
import lombok.Data;

import java.util.List;

@Data
public class MenuCategoryDTO {
    private List<Menu> menus;
    private String title;

}
