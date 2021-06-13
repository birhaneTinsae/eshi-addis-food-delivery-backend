package com.eshi.addis.dto;

import lombok.Data;

@Data
public class OrderMenuDTO {
    private MenuDTO menuItem;
    private Integer quantity;
}
