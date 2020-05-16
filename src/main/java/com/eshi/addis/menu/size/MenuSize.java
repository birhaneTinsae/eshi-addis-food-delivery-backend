package com.eshi.addis.menu.size;

import com.eshi.addis.menu.Menu;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MenuSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    @ManyToOne
    private Menu menu;
}

