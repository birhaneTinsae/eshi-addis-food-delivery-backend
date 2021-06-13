package com.eshi.addis.favourite;

import com.eshi.addis.customer.Customer;
import com.eshi.addis.menu.Menu;
import lombok.Data;

import java.io.Serializable;

@Data
public class FavouriteDTO implements Serializable {
    private Customer customer;
    private Menu menu;
}
