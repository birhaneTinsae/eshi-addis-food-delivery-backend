package com.eshi.addis.comment;

import com.eshi.addis.restaurant.Restaurant;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity(name = "comment_and_ratings")
@Data
public class CommentAndRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Max(value = 5,message = "Exceed max value ")
    @Min(value = 0,message = "Below min value")
    private int rating;
    private String comment;
    private boolean updated;
    private LocalDateTime createdAt;
    @ManyToOne
    private Restaurant restaurant;
}
