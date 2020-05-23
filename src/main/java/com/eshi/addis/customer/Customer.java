package com.eshi.addis.customer;

import com.eshi.addis.model.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "customers")
@Data
public class Customer {
    @GeneratedValue
    @Id
    private String id;
    private int points;
    @OneToMany
    private List<CustomerAddress> addresses;
}
