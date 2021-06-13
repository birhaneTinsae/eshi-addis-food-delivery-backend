package com.eshi.addis.dto;

import com.eshi.addis.model.*;
import com.eshi.addis.restaurant.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantMenuDTO {
    private String name;
    private Address address;
    private Contact contact;
    private int totalRating;
    private double rating;
    private String coverPic;
    private String description;
    private Pricing pricing;
    private String eat;
    private List<Category> menuCategories;
}
