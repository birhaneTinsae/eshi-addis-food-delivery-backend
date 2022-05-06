package com.eshi.addis.dto;

import com.eshi.addis.restaurant.RestaurantDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestaurantCategoryDTO {
    private String category;
    private List<RestaurantDto> restaurants =new ArrayList<>();
}
