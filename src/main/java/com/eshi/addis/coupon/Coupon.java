/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eshi.addis.coupon;

import java.io.Serializable;
import javax.persistence.*;

import com.eshi.addis.restaurant.Restaurant;
import lombok.Data;

/**
 *
 * @author birhane
 */
@Data
@Entity
public class Coupon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean percent;
    private double amount;
    private String coupon;
    private boolean global;
    @ManyToOne
    private Restaurant restaurant;
}
