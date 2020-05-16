package com.eshi.addis.restaurant;

import com.eshi.addis.comment.CommentAndRating;
import com.eshi.addis.menu.ingredient.Ingredient;
import com.eshi.addis.model.*;
import com.eshi.addis.order.Status;
import com.eshi.addis.order.StatusConverter;
import com.eshi.addis.restaurant.branch.Branch;
import com.eshi.addis.restaurant.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "restaurants")
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Restaurant name is required")
    @NotBlank(message = "Restaurant name shouldn't be blank")
    private String name;
    private String coverPic;
    private String description;
    @Convert(converter = PricingConverter.class)
    private Pricing pricing;
    @Convert(converter = StatusConverter.class)
    private Status status;
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
    @JsonIgnoreProperties(value = {"restaurant"})
    @OneToMany(mappedBy = "restaurant")
    private List<CommentAndRating> commentAndRatings;

    @JsonIgnoreProperties(value = {"restaurant"})
    @OneToMany(mappedBy = "restaurant")
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
        if (this.getCommentAndRatings() != null && this.getCommentAndRatings().size() > 0) {
            return getCommentAndRatings().size();
        }
        return 0;
    }
}
