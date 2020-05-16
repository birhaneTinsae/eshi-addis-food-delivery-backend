package com.eshi.addis.order;

import com.eshi.addis.order.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order,Long> {
    Page<Order> findAllByIdAndOrderStatus(long id,OrderStatus status);
}
