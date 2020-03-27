package com.eshi.addis.service;

import com.eshi.addis.dto.RestaurantDto;
import com.eshi.addis.model.Category;
import com.eshi.addis.model.Restaurant;
import com.eshi.addis.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RestaurantService {
    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant getServiceProvider(long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("can't found service provider with "+id));
    }

    public List<RestaurantDto> getServiceProviders() {
        List<RestaurantDto> restaurantDtos=new ArrayList<>();
        restaurantRepository.findAll().forEach(r->{
            RestaurantDto dto=new RestaurantDto();
            dto.setAddress(r.getAddress());
            dto.setContact(r.getContact());
            dto.setRating(r.getRating());
            dto.setTotalRating(r.getTotalRating());
            dto.setCoverPic(r.getCoverPic());
            dto.setName(r.getName());
            restaurantDtos.add(dto);

        });
        return restaurantDtos;
    }

    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
       return restaurantRepository.save(restaurant);


    }
    public Iterable<Category> getServiceProviderMenuCategories(long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(EntityExistsException::new);
        return restaurant.getMenuCategories();
    }
}
