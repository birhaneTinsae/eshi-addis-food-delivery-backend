package com.eshi.addis.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RestaurantCategoryDTO {
    private String category;
    private List<RestaurantDTO> restaurants =new ArrayList<>();
}
