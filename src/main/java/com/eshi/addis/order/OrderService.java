package com.eshi.addis.order;

import com.eshi.addis.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Order createOrder(OrderDTO orderDTO);

    Order updateOrder(String orderId, OrderDTO orderDTO);

    Order getOrder(String orderId);

    void acceptOrder(String orderId);

    void cancelOrder(String orderId);

    void deleteOrder(String orderId);

    Page<Order> getCustomerOrders(String customerId, Pageable pageable);

    Page<Order> getRestaurantOrders(String restaurantId, Pageable pageable);
}
