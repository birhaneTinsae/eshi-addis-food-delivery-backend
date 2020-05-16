package com.eshi.addis.order.orderMenu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMenuRepository extends JpaRepository<OrderMenu, OrderMenuPK> {
}
