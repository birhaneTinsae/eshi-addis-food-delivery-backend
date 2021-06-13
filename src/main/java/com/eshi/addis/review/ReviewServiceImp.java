package com.eshi.addis.review;

import com.eshi.addis.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class ReviewServiceImp implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review getReview(long reviewId) {
        return reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException(Review.class, "Id", String.valueOf(reviewId)));
    }

    @Override
    public Review updateReview(long reviewId, Review review) {
        var rating = getReview(reviewId);
        BeanUtils.copyProperties(review, rating, getNullPropertyNames(review));
        return reviewRepository.save(rating);
    }

    @Override
    public void deleteReview(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public Page<Review> getCustomerReviews(String customerId, Pageable pageable) {
        return reviewRepository.findAllByCustomer_Id(customerId, pageable);
    }

    @Override
    public Page<Review> getRestaurantReviews(String restaurantId, Pageable pageable) {
        return reviewRepository.findAllByRestaurant_Id(restaurantId, pageable);
    }

}
