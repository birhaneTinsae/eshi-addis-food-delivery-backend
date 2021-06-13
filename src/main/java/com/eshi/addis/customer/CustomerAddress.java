package com.eshi.addis.customer;

import com.eshi.addis.model.Address;
import com.eshi.addis.utils.Auditable;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CustomerAddress extends Auditable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String name;
    @Embedded
    private Address address;
    @ManyToOne
    private Customer customer;
}
