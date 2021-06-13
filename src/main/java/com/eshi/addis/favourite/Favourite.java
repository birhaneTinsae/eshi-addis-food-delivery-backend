package com.eshi.addis.favourite;

import com.eshi.addis.customer.Customer;
import com.eshi.addis.menu.Menu;
import com.eshi.addis.utils.Auditable;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Favourite extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Menu menu;
    @ManyToOne
    private Customer customer;
}
