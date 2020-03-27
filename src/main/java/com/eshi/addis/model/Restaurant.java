package com.eshi.addis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "restaurants")
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String coverPic;
    @JsonIgnoreProperties(value = {"restaurant"/*,"hibernateLazyInitializer"*/})
    @OneToMany(mappedBy = "restaurant")
    private List<Category> menuCategories;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;
    @JsonIgnoreProperties(value = {"restaurant"})
    @OneToMany(mappedBy = "restaurant")
    private List<Branch> branches;
    @OneToMany(mappedBy = "restaurant")
    private List<CommentAndRating> commentAndRatings;
    @OneToMany(mappedBy = "restaurant")
    @JsonIgnoreProperties(value = {"restaurant"})
    private List<Ingredient> ingredients;
    @Transient
    private double rating;
    @Transient
    private int totalRating;

    public double getRating() {
        int total = 0;
        if (this.getCommentAndRatings() != null && this.getCommentAndRatings().size() > 0) {
            total = this.getCommentAndRatings().stream().reduce(0, (subTotal, element) -> subTotal + element.getRating(), Integer::sum);
            return (double) total / getCommentAndRatings().size();
        }
        return total;
    }

    public int getTotalRating(){
        return getCommentAndRatings().size();
    }
}
