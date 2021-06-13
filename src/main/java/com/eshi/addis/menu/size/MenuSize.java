package com.eshi.addis.menu.size;

import com.eshi.addis.menu.Menu;
import com.eshi.addis.utils.Auditable;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "menu_sizes")
@Data
public class MenuSize extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    @ManyToOne(cascade=CascadeType.ALL)
    private Menu menu;
}

