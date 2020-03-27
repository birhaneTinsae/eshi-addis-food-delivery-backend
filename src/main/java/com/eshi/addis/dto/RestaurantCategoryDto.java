package com.eshi.addis.dto;

import com.eshi.addis.model.Restaurant;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class RestaurantCategoryDto {
    private String category;
    private List<RestaurantDto> restaurants =new ArrayList<>();
}
