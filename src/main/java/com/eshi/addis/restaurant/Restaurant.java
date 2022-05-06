package com.eshi.addis.restaurant;

import com.eshi.addis.menu.ingredient.Ingredient;
import com.eshi.addis.model.Address;
import com.eshi.addis.model.Contact;
import com.eshi.addis.model.Pricing;
import com.eshi.addis.model.PricingConverter;
import com.eshi.addis.order.Order;
import com.eshi.addis.order.Status;
import com.eshi.addis.order.StatusConverter;
import com.eshi.addis.restaurant.branch.Branch;
import com.eshi.addis.restaurant.category.Category;
import com.eshi.addis.review.Review;
import com.eshi.addis.restaurant.workingHour.WorkingHours;
import com.eshi.addis.utils.Auditable;
import com.eshi.addis.utils.StringPrefixedSequenceGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "restaurants")
public class Restaurant extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq")
    @GenericGenerator(
            name = "restaurant_seq",
            strategy = "com.eshi.addis.utils.StringPrefixedSequenceGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM, value = "50"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "EAS_"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;
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
    @ToString.Exclude
    private List<Category> menuCategories;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;

    @JsonIgnoreProperties(value = {"restaurant"})
    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    private List<Branch> branches;
    @JsonIgnoreProperties(value = {"restaurant"})
    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    private List<Review> reviews;

    @JsonIgnoreProperties(value = {"restaurant"})
    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    private List<Ingredient> ingredients;

    @Transient
    private double rating;
    @Transient
    private int totalRating;

    @OneToMany(mappedBy = "restaurant",cascade=CascadeType.ALL)
    @ToString.Exclude
    private List<WorkingHours> workingHours;
    @OneToMany(mappedBy = "restaurant")
    @ToString.Exclude
    private List<Order> orders;
    private boolean verified;

    @Transient
    public double getRating() {
        var total = 0;
        if (!isNull(this.getReviews()) && !this.getReviews().isEmpty()) {
            total = this.getReviews().stream().reduce(0, (subTotal, element) -> subTotal + element.getRating(), Integer::sum);
            return (double) total / getReviews().size();
        }
        return total;
    }

    @Transient
    public int getTotalRating() {
        if (!isNull(this.getReviews()) && !this.getReviews().isEmpty()) {
            return getReviews().size();
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Restaurant that = (Restaurant) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 1642335971;
    }
}
