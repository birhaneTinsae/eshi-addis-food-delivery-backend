package com.eshi.addis.service;

import com.eshi.addis.model.OrderMenu;
import com.eshi.addis.repository.OrderMenuRepository;
import com.eshi.addis.utils.Common;
import org.springframework.stereotype.Service;

@Service
public class OrderMenuService implements Common<OrderMenu> {
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
    public Iterable<OrderMenu> getAll() {
        return orderMenuRepository.findAll();
    }

    @Override
    public OrderMenu getOne(long id) {
        return null;
    }
}
