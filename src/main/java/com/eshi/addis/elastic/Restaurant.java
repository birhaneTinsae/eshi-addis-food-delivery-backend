package com.eshi.addis.elastic;

import com.eshi.addis.model.Address;
import com.eshi.addis.model.Contact;
import com.eshi.addis.restaurant.workingHour.WorkingHourDto;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(indexName = "restaurants")
public class Restaurant {
    @Id
    private String id;
    @Field(type = FieldType.Text, name = "name")
    private String name;
    @Field(type = FieldType.Text, name = "coverPic")
    private String coverPic;
    @Field(type = FieldType.Text, name = "description")
    private String description;
    //    @Convert(converter = PricingConverter.class)
//    private Pricing pricing;
//    @Convert(converter = StatusConverter.class)
//    private Status status;
//    private List<Category> menuCategories;
    @Field(type = FieldType.Nested)
    private Address address;
    @Field(type = FieldType.Nested)
    private Contact contact;

    //    private List<Branch> branches;
//    private List<Review> reviews;
//    private List<Ingredient> ingredients;
//    private double rating;
//    private int totalRating;
    @Field(type = FieldType.Nested)
    private List<WorkingHourDto> workingHours;
    //    private List<Order> orders;
    private boolean verified;

}
