package com.eshi.addis.order;

import com.eshi.addis.dto.OrderDto;
import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.order.Order;
import com.eshi.addis.order.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order order) {
        return orderRepository.save(order);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

    public Order getOrder(long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Order.class, "id", String.valueOf(id)));
    }

    public Page<Order> getOrderByStatus(long id, OrderStatus orderStatus) {
        return orderRepository.findAllByIdAndOrderStatus(id, orderStatus);
    }
}
