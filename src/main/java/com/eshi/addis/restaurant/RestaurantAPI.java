package com.eshi.addis.restaurant;

import com.eshi.addis.dto.RestaurantDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

public interface RestaurantAPI {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    RestaurantDTO createRestaurant(@Valid() @RequestBody() Restaurant restaurant);

    @GetMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    RestaurantDTO getRestaurant(@PathVariable String restaurantId);

    @PutMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    RestaurantDTO updateRestaurant(@PathVariable String restaurantId, @Valid() @RequestBody() Restaurant restaurant);

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteRestaurant(@PathVariable String restaurantId);

    @PostMapping("/cover-pic/{restaurantId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void uploadCoverPic(@PathVariable String restaurantId, MultipartFile coverPic);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<RestaurantDTO>> getRestaurants(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                             @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
    );

    @GetMapping("/nearby")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<RestaurantDTO>> getNearbyRestaurants(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                   @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @RequestParam("location") Point customLocation);

    @GetMapping("/recommended/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<RestaurantDTO>> getRecommendedRestaurants(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                        @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable("customerId") String customerId);

    @GetMapping("/new-restaurants")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<RestaurantDTO>> getNewRestaurants(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
    );

    @GetMapping("/customer-favourites/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<RestaurantDTO>> getCustomerFavourites(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                    @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable("customerId") String customerId);


    List<WorkingHourDTO> addWorkingHours(String restaurantId, List<WorkingHourDTO> workingHours);

    WorkingHourDTO updateWorkingHour(String restaurantId, WorkingHourDTO workingHour);
}
