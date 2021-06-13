package com.eshi.addis.restaurant;

import com.eshi.addis.dto.RestaurantDTO;
import com.eshi.addis.favourite.FavouriteDTO;
import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

import static com.eshi.addis.utils.Util.dtoMapper;
import static com.eshi.addis.utils.Util.mapList;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController implements RestaurantAPI {

    private final RestaurantService restaurantService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public RestaurantDTO createRestaurant(Restaurant restaurant) {
        return dtoMapper(restaurantService.createRestaurant(restaurant), RestaurantDTO.class, modelMapper);
    }

    @Override
    public RestaurantDTO getRestaurant(String restaurantId) {
        return dtoMapper(restaurantService.getRestaurant(restaurantId), RestaurantDTO.class, modelMapper);
    }

    @Override
    public RestaurantDTO updateRestaurant(String restaurantId, Restaurant restaurant) {
        return dtoMapper(restaurantService.updateRestaurant(restaurantId, restaurant), RestaurantDTO.class, modelMapper);
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
    public ResponseEntity<PagedModel<RestaurantDTO>> getNearbyRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, Point customerLocation) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDTO.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getNearbyRestaurants(customerLocation, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDTO>>(assembler.toModel(restaurantService.getNearbyRestaurants(customerLocation, pageable).map(favourite -> dtoMapper(favourite, FavouriteDTO.class, modelMapper))), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDTO>> getRecommendedRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String customerId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDTO.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getRecommendedRestaurants(customerId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDTO>>(assembler.toModel(restaurantService.getRecommendedRestaurants(customerId, pageable).map(favourite -> dtoMapper(favourite, FavouriteDTO.class, modelMapper))), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDTO>> getNewRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDTO.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getNewRestaurants(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDTO>>(assembler.toModel(restaurantService.getNewRestaurants(pageable).map(favourite -> dtoMapper(favourite, FavouriteDTO.class, modelMapper))), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDTO>> getCustomerFavourites(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String customerId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDTO.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getCustomerFavourites(customerId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDTO>>(assembler.toModel(restaurantService.getCustomerFavourites(customerId, pageable).map(favourite -> dtoMapper(favourite, FavouriteDTO.class, modelMapper))), HttpStatus.OK);

    }

    @Override
    public List<WorkingHourDTO> addWorkingHours(String restaurantId, List<WorkingHourDTO> workingHours) {
        return mapList((List<WorkingHours>) restaurantService.addWorkingHours(restaurantId, workingHours), WorkingHourDTO.class, modelMapper);
    }

    @Override
    public WorkingHourDTO updateWorkingHour(String restaurantId, WorkingHourDTO workingHour) {
        return dtoMapper(restaurantService.updateWorkingHours(restaurantId, workingHour), WorkingHourDTO.class, modelMapper);
    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDTO>> getRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDTO.class, uriBuilder, response, pageable.getPageNumber(), restaurantService.getRestaurants(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDTO>>(assembler.toModel(restaurantService.getRestaurants(pageable).map(restaurant -> dtoMapper(restaurant, RestaurantDTO.class, modelMapper))), HttpStatus.OK);

    }


}
