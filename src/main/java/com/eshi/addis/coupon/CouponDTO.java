package com.eshi.addis.coupon;

import com.eshi.addis.restaurant.Restaurant;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CouponDTO implements Serializable {
    private boolean percentage;
    private double amount;
    private int qty;
    private boolean global;
    private LocalDate expiryDate;
    private boolean used;
    private Restaurant restaurant;
}
