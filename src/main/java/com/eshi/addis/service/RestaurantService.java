package com.eshi.addis.service;

import com.eshi.addis.dto.RestaurantDto;
import com.eshi.addis.dto.RestaurantMenuDto;
import com.eshi.addis.model.Category;
import com.eshi.addis.model.Restaurant;
import com.eshi.addis.model.Status;
import com.eshi.addis.repository.RestaurantRepository;
import com.eshi.addis.utils.Common;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
public class RestaurantService implements Common<Restaurant, Restaurant> {
    private RestaurantRepository restaurantRepository;
    private ModelMapper mapper;

    public RestaurantService(RestaurantRepository restaurantRepository, ModelMapper mapper) {
        this.restaurantRepository = restaurantRepository;
        this.mapper = mapper;
    }

    public Restaurant getServiceProvider(long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("can't found service provider with " + id));
    }

    public RestaurantDto getServiceProviderDto(long id) {
        Restaurant restaurant = show(id);
        RestaurantDto restaurantDto = mapper.map(restaurant, RestaurantDto.class);
        return restaurantDto;
    }

    public List<RestaurantDto> getServiceProviders() {
        List<RestaurantDto> restaurantDtos = new ArrayList<>();
        restaurantRepository.findAll().forEach(r -> {
            RestaurantDto dto = mapper.map(r, RestaurantDto.class);
            dto.setEat("20-30 MIN");
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

    public Iterable<Category> getServiceProviderMenuCategories(long id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(EntityExistsException::new);
        return restaurant.getMenuCategories();
    }

    @Override
    public Restaurant store(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Iterable<Restaurant> store(List<Restaurant> t) {
        return restaurantRepository.saveAll(t);
    }

    @Override
    public Restaurant show(long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Restaurant with id " + id + " not found"));
    }

    @Override
    public Restaurant update(long id, Restaurant restaurant) {
        Restaurant r = show(id);
        BeanUtils.copyProperties(restaurant, r, getNullPropertyNames(restaurant));
        return restaurantRepository.save(r);
    }

    @Override
    public boolean delete(long id) {
        Restaurant res = show(id);
        res.setStatus(Status.DELETED);
        restaurantRepository.save(res);
        return true;
    }

    @Override
    public Page<Restaurant> getAll(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    public RestaurantMenuDto getRestaurant(long id) {
        Restaurant restaurant = show(id);
        return mapper.map(restaurant,RestaurantMenuDto.class);
    }
}
