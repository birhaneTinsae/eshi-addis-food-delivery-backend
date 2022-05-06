package com.eshi.addis.dto;

import com.eshi.addis.menu.MenuDto;
import lombok.Data;

@Data
public class OrderMenuDTO {
    private MenuDto menuItem;
    private Integer quantity;
}
