package com.eshi.addis.customer;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "customers")
@Data
public class Customer {
    @GeneratedValue
    @Id
    private String id;
}
