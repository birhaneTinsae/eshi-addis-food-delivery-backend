package com.eshi.addis.restaurant;

import com.eshi.addis.dto.RestaurantCategoryDto;
import com.eshi.addis.dto.HomeDto;
import com.eshi.addis.dto.RestaurantMenuDto;
import com.eshi.addis.utils.Common;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("service-providers")
public class RestaurantController implements Common<Restaurant, Restaurant> {

    private RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping("/{id}/mobile")
    public RestaurantMenuDto getRestaurant(@PathVariable long id){
        return service.getRestaurant(id);
    }

    @Override
    public Restaurant store(@Valid @RequestBody Restaurant restaurant) {
        return service.store(restaurant);
    }

    @Override
    public Iterable<Restaurant> store(List<@Valid Restaurant> t) {
        return service.store(t);
    }

    @Override
    public Restaurant show(long id) {
        return service.show(id);
    }


    public Restaurant update(@PathVariable long id,@Valid @RequestBody Restaurant restaurant) {
        return service.update(id, restaurant);
    }

    @Override
    public boolean delete(long id) {
        return service.delete(id);
    }

    @Override
    public Iterable<Restaurant> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/home")
    public HomeDto index(@RequestBody(required = false) Point location) {
        HomeDto homeDto = new HomeDto();

        List<RestaurantCategoryDto> restaurantCategoryDtos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            RestaurantCategoryDto restaurantCategoryDto = new RestaurantCategoryDto();
            restaurantCategoryDto.setCategory(getCategories()[i]);
            restaurantCategoryDto.setRestaurants(service.getRestaurants());
            restaurantCategoryDtos.add(restaurantCategoryDto);
        }
        homeDto.setRestaurantCategories(restaurantCategoryDtos);

        return homeDto;
    }

    public static String[] getCategories() {
        return new String[]{"Favourite", "Nearby", "Recommended", "New On Eshi"};
    }
}
