package com.eshi.addis.restaurant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantService {

    Restaurant createRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(String restaurantId);

    Restaurant updateRestaurant(String restaurantId, Restaurant restaurant);

    void deleteRestaurant(String restaurantId);

    void uploadCoverPic(String restaurantId, MultipartFile coverPic);

    Page<Restaurant> getRestaurants(Pageable pageable);

    Page<Restaurant> getNearbyRestaurants(Point location, Pageable pageable);

    Page<Restaurant> getRecommendedRestaurants(String customerId, Pageable pageable);

    Page<Restaurant> getNewRestaurants(Pageable pageable);

    Page<Restaurant> getCustomerFavourites(String customerId, Pageable pageable);

    Iterable<WorkingHours> addWorkingHours(String restaurantId, List<WorkingHourDTO> workingHour);
    WorkingHours updateWorkingHours(String restaurantId,WorkingHourDTO workingHour);
}
