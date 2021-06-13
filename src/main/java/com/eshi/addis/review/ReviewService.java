package com.eshi.addis.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {


    Review createReview(Review review);

    Review getReview(long reviewId);

    Review updateReview(long reviewId, Review review);

    void deleteReview(long reviewId);

    Page<Review> getCustomerReviews(String customerId, Pageable pageable);

    Page<Review> getRestaurantReviews(String restaurantId, Pageable pageable);
}
