package com.eshi.addis.restaurant.branch;

import com.eshi.addis.model.Address;
import com.eshi.addis.model.Contact;
import com.eshi.addis.restaurant.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "branches")
public class Branch implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @JsonIgnoreProperties(value = {"branches"})
    @ManyToOne
    private Restaurant restaurant;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;
}
