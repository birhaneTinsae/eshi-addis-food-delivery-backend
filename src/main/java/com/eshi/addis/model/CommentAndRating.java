package com.eshi.addis.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity(name = "comment_and_ratings")
@Data
public class CommentAndRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Max(value = 5,message = "Exceed max value ")
    private int rating;
    private String comment;
    @ManyToOne
    private Restaurant restaurant;
}
