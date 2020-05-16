package com.eshi.addis.order.orderMenu;

import com.eshi.addis.utils.Common;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMenuService implements Common<OrderMenu,OrderMenu> {
    private OrderMenuRepository orderMenuRepository;

    public OrderMenuService(OrderMenuRepository orderMenuRepository) {
        this.orderMenuRepository = orderMenuRepository;
    }

    public OrderMenu create(OrderMenu orderMenu) {
        return orderMenuRepository.save(orderMenu);
    }

    public OrderMenu update(OrderMenu orderMenu) {
        return orderMenuRepository.save(orderMenu);
    }



    @Override
    public OrderMenu store(OrderMenu orderMenu) {
        return null;
    }

    @Override
    public Iterable<OrderMenu> store(List<OrderMenu> t) {
        return null;
    }

    @Override
    public OrderMenu show(long id) {
        return null;
    }

    @Override
    public OrderMenu update(long id, OrderMenu orderMenu) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Iterable<OrderMenu> getAll(Pageable pageable) {
        return null;
    }
}
