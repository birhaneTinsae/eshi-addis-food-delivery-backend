package com.eshi.addis.dto;

import com.eshi.addis.coupon.Coupon;
import com.eshi.addis.model.Address;
import com.eshi.addis.order.OrderStatus;
import com.eshi.addis.restaurant.Restaurant;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;
    private List<OrderMenuDTO> menus ;
    private String specialNote;
    private LocalDateTime deliveryAt=LocalDateTime.now();
    private Address deliveryLocation;
    private OrderStatus status;
    private Restaurant restaurant;
    private Coupon coupon;

}
