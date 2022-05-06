package com.eshi.addis.restaurant;

import com.eshi.addis.model.Address;
import com.eshi.addis.model.Contact;
import com.eshi.addis.model.Pricing;
import com.eshi.addis.restaurant.workingHour.WorkingHourDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto implements Serializable {
    private String id;
    private String name;
    private Address address;
    private Contact contact;
    private int totalRating;
    private double rating;
    private String coverPic;
    private String description;
    private Pricing pricing;
    private String eat;
    private boolean verified;
    private List<WorkingHourDto> workingHours;
}
