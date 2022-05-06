package com.eshi.addis.restaurant;

import com.eshi.addis.elastic.ElasticRestaurantService;
import com.eshi.addis.restaurant.workingHour.WorkingHourDto;
import com.eshi.addis.restaurant.workingHour.WorkingHourMapper;
import com.eshi.addis.restaurant.workingHour.WorkingHours;
import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController implements RestaurantAPI {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final ApplicationEventPublisher eventPublisher;
    private final ElasticRestaurantService service;
    private final WorkingHourMapper workingHourMapper;

    @Override
    public RestaurantDto createRestaurant(Restaurant restaurant) {
        return restaurantMapper.toRestaurantDTO(restaurantService.createRestaurant(restaurant));
    }

    @Override
    public RestaurantDto getRestaurant(String restaurantId) {
        return restaurantMapper.toRestaurantDTO(restaurantService.getRestaurant(restaurantId));
    }

    @Override
    public RestaurantDto updateRestaurant(String restaurantId, Restaurant restaurant) {
        return restaurantMapper.toRestaurantDTO(restaurantService.updateRestaurant(restaurantId, restaurant));
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }

    @Override
    public void uploadCoverPic(String restaurantId, MultipartFile coverPic) {
        restaurantService.uploadCoverPic(restaurantId, coverPic);
    }


    @Override
    public ResponseEntity<PagedModel<RestaurantDto>> getNearbyRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, Point customerLocation) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDto.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getNearbyRestaurants(customerLocation, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDto>>(assembler.toModel(restaurantService.getNearbyRestaurants(customerLocation, pageable).map(restaurantMapper::toRestaurantDTO)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDto>> getRecommendedRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String customerId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDto.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getRecommendedRestaurants(customerId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDto>>(assembler.toModel(restaurantService.getRecommendedRestaurants(customerId, pageable).map(restaurantMapper::toRestaurantDTO)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDto>> getNewRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDto.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getNewRestaurants(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDto>>(assembler.toModel(restaurantService.getNewRestaurants(pageable).map(restaurantMapper::toRestaurantDTO)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDto>> getCustomerFavourites(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String customerId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDto.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getCustomerFavourites(customerId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDto>>(assembler.toModel(restaurantService.getCustomerFavourites(customerId, pageable).map(restaurantMapper::toRestaurantDTO)), HttpStatus.OK);

    }

    @Override
    public List<WorkingHourDto> addWorkingHours(String restaurantId, List<WorkingHourDto> workingHours) {
        return workingHourMapper.toWorkingHours((List<WorkingHours>) restaurantService.addWorkingHours(restaurantId, workingHours));
    }

    @Override
    public WorkingHourDto updateWorkingHour(String restaurantId, WorkingHourDto workingHour) {
        return workingHourMapper.toWorkingHour(restaurantService.updateWorkingHours(restaurantId, workingHour));
    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDto>> getRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDto.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getRestaurants(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDto>>(assembler.toModel(restaurantService.getRestaurants(pageable).map(restaurantMapper::toRestaurantDTO)), HttpStatus.OK);

    }




}
