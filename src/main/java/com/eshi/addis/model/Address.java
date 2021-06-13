package com.eshi.addis.model;

import lombok.Data;
import org.springframework.data.geo.Point;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Data
public class Address implements Serializable {
    @NotBlank(message = "City name shouldn't be blank")
    @NotNull(message = "City is required")
    private String city;
    private String street;
    private String houseNo;
    private String blockNo;
    private String landmark;
    @NotNull(message = "Location is required")
    private Point location;
}
