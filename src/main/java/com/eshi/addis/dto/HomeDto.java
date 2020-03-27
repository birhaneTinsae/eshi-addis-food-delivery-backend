package com.eshi.addis.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomeDto {
    private List<RestaurantCategoryDto> restaurantCategories;

}
