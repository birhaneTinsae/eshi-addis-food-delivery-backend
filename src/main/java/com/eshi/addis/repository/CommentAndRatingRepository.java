package com.eshi.addis.repository;

import com.eshi.addis.model.CommentAndRating;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentAndRatingRepository extends PagingAndSortingRepository<CommentAndRating,Long> {
}
