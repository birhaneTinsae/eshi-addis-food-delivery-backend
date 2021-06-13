package com.eshi.addis.order.orderMenu;

import com.eshi.addis.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderMenuService implements Common<OrderMenu, OrderMenu> {
    private final OrderMenuRepository orderMenuRepository;

    @Override
    public OrderMenu store(OrderMenu orderMenu) {
        return orderMenuRepository.save(orderMenu);
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
        return orderMenuRepository.save(orderMenu);
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
