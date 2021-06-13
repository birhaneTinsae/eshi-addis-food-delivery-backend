/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eshi.addis.coupon;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

import com.eshi.addis.restaurant.Restaurant;
import com.eshi.addis.utils.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author birhane
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Coupon extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean percentage;
    private double amount;
    private String code;
    private boolean global;
    @ManyToOne
    private Restaurant restaurant;
    private LocalDate expiryDate;
    private boolean used;
}
