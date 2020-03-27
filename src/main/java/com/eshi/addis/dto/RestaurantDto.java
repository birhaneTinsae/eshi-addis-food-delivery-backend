package com.eshi.addis.dto;

import com.eshi.addis.model.Address;
import com.eshi.addis.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    private String name;
    private Address address;
    private Contact contact;
    private int totalRating;
    private double rating;
    private String coverPic;
}
