package com.eshi.addis.order;

import com.eshi.addis.dto.OrderDTO;
import com.google.maps.errors.ApiException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface OrderService {

    Order createOrder(OrderDTO orderDTO) throws IOException, InterruptedException, ApiException;

    Order updateOrder(String orderId, OrderDTO orderDTO);

    Order getOrder(String orderId);

    void acceptOrder(String orderId);

    void cancelOrder(String orderId);

    void deleteOrder(String orderId);

    Page<Order> getCustomerOrders(String customerId, Pageable pageable);

    Page<Order> getRestaurantOrders(String restaurantId, Pageable pageable);
}
