package com.eshi.addis.review;

import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController implements ReviewAPI {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ReviewDTO createReview(ReviewDTO review) {
        return reviewMapper.toReviewDTO(reviewService.createReview(reviewMapper.toReview(review)));
    }

    @Override
    public ReviewDTO getReview(long reviewId) {
        return reviewMapper.toReviewDTO(reviewService.getReview(reviewId));

    }

    @Override
    public ReviewDTO updateReview(long reviewId, ReviewDTO review) {
        return reviewMapper.toReviewDTO(reviewService.updateReview(reviewId, reviewMapper.toReview(review)));

    }

    @Override
    public void deleteReview(long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @Override
    public ResponseEntity<PagedModel<ReviewDTO>> getCustomerReviews(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String customerId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ReviewDTO.class, uriBuilder, response, pageable.getPageNumber(), reviewService.getCustomerReviews(customerId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ReviewDTO>>(assembler.toModel(reviewService.getCustomerReviews(customerId, pageable).map(reviewMapper::toReviewDTO)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<ReviewDTO>> getRestaurantReviews(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String restaurantId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ReviewDTO.class, uriBuilder, response, pageable.getPageNumber(), reviewService.getRestaurantReviews(restaurantId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ReviewDTO>>(assembler.toModel(reviewService.getRestaurantReviews(restaurantId, pageable).map(reviewMapper::toReviewDTO)), HttpStatus.OK);

    }

}
