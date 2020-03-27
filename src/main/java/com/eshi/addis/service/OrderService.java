package com.eshi.addis.service;

import com.eshi.addis.model.Order;
import com.eshi.addis.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order order){
        return orderRepository.save(order);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

    public Order getOrder(long id) {
        return orderRepository.getOne(id);
    }
}
