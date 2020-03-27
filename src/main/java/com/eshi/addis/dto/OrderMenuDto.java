package com.eshi.addis.dto;

import lombok.Data;

@Data
public class OrderMenuDto {
    private MenuDto menuItem;
    private Integer quantity;
}
