package com.eshi.addis.customer;

import com.eshi.addis.favourite.Favourite;
import com.eshi.addis.utils.Auditable;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name = "customers")
@Data
public class Customer extends Auditable {
    @GeneratedValue
    @Id
    private String id;
    private int points;
    @OneToMany(mappedBy="customer")
    private List<CustomerAddress> addresses;
    @OneToMany
    private List<Favourite> favourites;
}
