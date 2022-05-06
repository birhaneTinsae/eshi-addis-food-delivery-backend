package com.eshi.addis.restaurant;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant toRestaurant(RestaurantDto restaurantDTO);

    RestaurantDto toRestaurantDTO(Restaurant restaurant);


}
