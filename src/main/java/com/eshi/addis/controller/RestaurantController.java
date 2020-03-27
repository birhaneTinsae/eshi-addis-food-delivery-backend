package com.eshi.addis.controller;

import com.eshi.addis.dto.RestaurantCategoryDto;
import com.eshi.addis.dto.HomeDto;
import com.eshi.addis.model.Restaurant;
import com.eshi.addis.service.RestaurantService;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("service-providers")
public class RestaurantController {

    private RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @PostMapping
    public Restaurant create(@Valid @RequestBody Restaurant restaurant) {
        return service.create(restaurant);
    }

    @PutMapping("/{id}")
    public Restaurant update(@PathVariable long id, @RequestBody Restaurant restaurant) {
        return service.update(restaurant);
    }

    @GetMapping
    public HomeDto index(@RequestBody(required = false) Point location) {
        HomeDto homeDto = new HomeDto();

        List<RestaurantCategoryDto> restaurantCategoryDtos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RestaurantCategoryDto restaurantCategoryDto = new RestaurantCategoryDto();
            restaurantCategoryDto.setCategory(getCategories()[i]);
            restaurantCategoryDto.setRestaurants(service.getServiceProviders());
            restaurantCategoryDtos.add(restaurantCategoryDto);
        }
        homeDto.setRestaurantCategories(restaurantCategoryDtos);

        return homeDto;
    }

    public static String[] getCategories() {
        return new String[]{"Favourite", "Nearby", "Recommended", "New On Eshi"};
    }
}
