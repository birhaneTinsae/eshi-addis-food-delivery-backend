package com.eshi.addis.order;

import com.eshi.addis.order.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order,String> {
    Page<Order> findAllByIdAndOrderStatus(String id, OrderStatus status, Pageable pageable);

    Page<Order> findAllByRestaurant_Id(String restaurantId,Pageable pageable);

    Page<Order> findAllByCustomer_Id(String customerId, Pageable pageable);

}
