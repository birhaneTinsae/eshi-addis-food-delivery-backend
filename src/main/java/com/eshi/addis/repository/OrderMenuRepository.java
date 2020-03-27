package com.eshi.addis.repository;

import com.eshi.addis.model.OrderMenu;
import com.eshi.addis.model.OrderMenuPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMenuRepository extends JpaRepository<OrderMenu, OrderMenuPK> {
}
