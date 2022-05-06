package com.eshi.addis.review;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(ReviewDTO reviewDTO);

    ReviewDTO toReviewDTO(Review review);

}
