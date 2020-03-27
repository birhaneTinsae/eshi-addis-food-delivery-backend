package com.eshi.addis.model;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {
    private String city;
    private String street;
    private String houseNo;
    private Point location;
}
