package com.eshi.addis.service;

import com.eshi.addis.model.CommentAndRating;
import com.eshi.addis.model.Restaurant;
import com.eshi.addis.repository.CommentAndRatingRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class CommentAndRatingService {
    private CommentAndRatingRepository commentAndRatingRepository;
    private RestaurantService restaurantService;
    // private UserService userService;

    public CommentAndRatingService(CommentAndRatingRepository commentAndRatingRepository, RestaurantService restaurantService/*, UserService userService*/) {
        this.commentAndRatingRepository = commentAndRatingRepository;
        this.restaurantService = restaurantService;
        //       this.userService = userService;
    }


    public CommentAndRating create(long laundryId, long userId, CommentAndRating commentAndRating) {
        Restaurant restaurant = restaurantService.show(laundryId);
        // User user = userService.getUser(userId);
        //CommentAndRating c = new CommentAndRating();
        commentAndRating.setRestaurant(restaurant);
        //      commentAndRating.setUser(user);
        commentAndRating.setComment(commentAndRating.getComment());
        commentAndRating.setRating(commentAndRating.getRating());
        commentAndRating.setCreatedAt(LocalDateTime.now());
        return commentAndRatingRepository.save(commentAndRating);
    }

    public boolean delete(long id) {
        commentAndRatingRepository.deleteById(id);
        return true;
    }

    public CommentAndRating update(long id, CommentAndRating commentAndRating) {
        CommentAndRating c = commentAndRatingRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Comment with id " + id + " not found"));
        c.setUpdated(true);
        c.setRating(commentAndRating.getRating());
        c.setComment(commentAndRating.getComment());
        return commentAndRatingRepository.save(c);
    }
}
