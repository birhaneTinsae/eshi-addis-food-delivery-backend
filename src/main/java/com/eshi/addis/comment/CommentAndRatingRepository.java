package com.eshi.addis.comment;

import com.eshi.addis.comment.CommentAndRating;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentAndRatingRepository extends PagingAndSortingRepository<CommentAndRating,Long> {
}
