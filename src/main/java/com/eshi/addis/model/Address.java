package com.eshi.addis.model;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
public class Address {
    @NotBlank(message = "City name shouldn't be blank")
    @NotNull(message = "City is required")
    private String city;
    private String street;
    private String houseNo;

    @NotNull(message = "Location is required")
    private Point location;
}
