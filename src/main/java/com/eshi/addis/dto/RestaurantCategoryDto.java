package com.eshi.addis.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestaurantCategoryDto {
    private String category;
    private List<RestaurantDto> restaurants =new ArrayList<>();
}
