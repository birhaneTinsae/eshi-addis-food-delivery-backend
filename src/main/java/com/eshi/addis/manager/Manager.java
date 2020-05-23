package com.eshi.addis.manager;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "managers")
@Data
public class Manager {
    @GeneratedValue
    @Id
    private String id;
}
