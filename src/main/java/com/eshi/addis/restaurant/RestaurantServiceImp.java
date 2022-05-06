package com.eshi.addis.restaurant;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.favourite.FavouriteService;
import com.eshi.addis.order.Status;
import com.eshi.addis.restaurant.workingHour.WorkingHourDto;
import com.eshi.addis.restaurant.workingHour.WorkingHourMapper;
import com.eshi.addis.restaurant.workingHour.WorkingHourRepository;
import com.eshi.addis.restaurant.workingHour.WorkingHours;
import com.eshi.addis.storage.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImp implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final StorageService storageService;
    private final FavouriteService favoriteService;
    private final WorkingHourRepository workingHourRepository;
    private final WorkingHourMapper workingHourMapper;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(String restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(Restaurant.class, "ID", restaurantId));
    }

    @Override
    public Restaurant updateRestaurant(String restaurantId, Restaurant restaurant) {
        var r = getRestaurant(restaurantId);
        BeanUtils.copyProperties(restaurant, r, getNullPropertyNames(restaurant));
        return restaurantRepository.save(r);
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        var res = getRestaurant(restaurantId);
        res.setStatus(Status.DELETED);
        restaurantRepository.save(res);
    }

    @Override
    public void uploadCoverPic(String restaurantId, MultipartFile coverPic) {
        var restaurant = getRestaurant(restaurantId);
        var filename = restaurant.getName().concat(LocalDateTime.now().toString());
        restaurant.setCoverPic(filename);
        storageService.store(filename, coverPic);
        restaurantRepository.save(restaurant);
    }

    @Override
    public Page<Restaurant> getRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public Page<Restaurant> getNearbyRestaurants(Point location, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Restaurant> getRecommendedRestaurants(String customerId, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Restaurant> getNewRestaurants(Pageable pageable) {
        return null;
    }

    @Override
    public Page<Restaurant> getCustomerFavourites(String customerId, Pageable pageable) {
        return favoriteService.getCustomerFavouriteRestaurants(customerId, pageable);
    }

    @Override
    public Iterable<WorkingHours> addWorkingHours(String restaurantId, List<WorkingHourDto> workingHours) {
        var restaurant = getRestaurant(restaurantId);
        List<WorkingHours> hours = workingHours.stream().map(workingHour -> {
            var wh = new WorkingHours();
            var workingHourKey = new WorkingHourKey(restaurantId, workingHour.getWeekDay().getId());
            wh.setId(workingHourKey);
            wh.setRestaurant(restaurant);
            wh.setWeekDay(workingHourMapper.toWeekDay(workingHour.getWeekDay()));
            wh.setCloseAt(workingHour.getCloseAt());
            wh.setOpenAt(workingHour.getOpenAt());
            return wh;
        }).collect(Collectors.toList());

        return workingHourRepository.saveAll(hours);
    }

    @Override
    public WorkingHours updateWorkingHours(String restaurantId, WorkingHourDto workingHour) {
        var workingHourKey = new WorkingHourKey(restaurantId, workingHour.getWeekDay().getId());
        var workingHours = getWorkingHours(workingHourKey);
        workingHours.setOpenAt(workingHour.getOpenAt());
        workingHours.setCloseAt(workingHour.getCloseAt());
        workingHours.setClosed(workingHour.isClosed());
        return workingHourRepository.save(workingHours);
    }

    private WorkingHours getWorkingHours(WorkingHourKey workingHourKey) {
        return workingHourRepository.findById(workingHourKey)
                .orElseThrow(() -> new EntityNotFoundException(WorkingHours.class, "Id", workingHourKey.toString()));
    }

}
