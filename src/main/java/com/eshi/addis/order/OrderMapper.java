package com.eshi.addis.order;

import com.eshi.addis.dto.OrderDTO;
import com.eshi.addis.dto.OrderMenuDTO;
import com.eshi.addis.menu.Menu;
import com.eshi.addis.menu.MenuDto;
import com.eshi.addis.order.orderMenu.OrderMenu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(OrderDTO orderDTO);

    OrderDTO toOrderDTO(Order order);

    OrderMenuDTO toOrderMenuDTO(OrderMenu orderMenu);

    OrderMenu toOrderMenu(OrderMenuDTO orderMenuDTO);

    MenuDto toMenuDTO(Menu menu);
    Menu toMenu(MenuDto menu);
}
