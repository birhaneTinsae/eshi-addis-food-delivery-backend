package com.eshi.addis.review;

import com.eshi.addis.customer.Customer;
import com.eshi.addis.restaurant.Restaurant;
import com.eshi.addis.utils.Auditable;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity(name = "reviews")
@Data
public class Review extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Max(value = 5,message = "Exceed max value ")
    @Min(value = 0,message = "Below min value")
    private int rating;
    private String comment;
    private boolean updated;
    @ManyToOne(cascade=CascadeType.ALL)
    private Restaurant restaurant;
    @ManyToOne(cascade=CascadeType.ALL)
    private Customer customer;
}
