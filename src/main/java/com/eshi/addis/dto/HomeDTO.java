package com.eshi.addis.dto;

import lombok.Data;

import java.util.List;

@Data
public class HomeDTO {
    private List<RestaurantCategoryDTO> restaurantCategories;

}
