package com.eshi.addis.elastic;

import org.springframework.data.geo.Point;

import java.util.List;

public interface ElasticRestaurantService {

    List<Restaurant> getNearbyRestaurants(Point point);

    List<Restaurant> search(String query);

    void updateRestaurants();

}
