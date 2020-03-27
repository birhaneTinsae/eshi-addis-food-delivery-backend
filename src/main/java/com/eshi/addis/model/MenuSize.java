package com.eshi.addis.model;

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

