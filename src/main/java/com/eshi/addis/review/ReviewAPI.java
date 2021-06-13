package com.eshi.addis.review;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface ReviewAPI {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ReviewDTO createReview(ReviewDTO review);

    @GetMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    ReviewDTO getReview(@PathVariable long reviewId);

    @PutMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReviewDTO updateReview(@PathVariable long reviewId, @Valid @RequestBody() ReviewDTO review);

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteReview(@PathVariable long reviewId);


    @GetMapping("/customer-reviews/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ReviewDTO>> getCustomerReviews(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                             @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable String customerId
    );

    @GetMapping("/restaurant-reviews/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<ReviewDTO>> getRestaurantReviews(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                               @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable String restaurantId
    );

}
