package com.eshi.addis.order;

import com.eshi.addis.menu.MenuService;
import com.eshi.addis.order.orderMenu.OrderMenu;
import com.eshi.addis.dto.OrderDto;
import com.eshi.addis.dto.OrderMenuDto;
import com.eshi.addis.order.orderMenu.OrderMenuService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController {
    private MenuService menuService;
    private OrderService orderService;
    private OrderMenuService orderMenuService;

    public OrderController(MenuService menuService, OrderService orderService, OrderMenuService orderMenuService) {
        this.menuService = menuService;
        this.orderService = orderService;
        this.orderMenuService = orderMenuService;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody OrderDto orderDto) {
        List<OrderMenuDto> orderMenuDtos = orderDto.getMenus();
        validateProductsExistence(orderMenuDtos);
        Order order = new Order();
        order.setOrderStatus(OrderStatus.PREPARING);
        order = this.orderService.create(order);

        List<OrderMenu> orderMenus = new ArrayList<>();


        for (OrderMenuDto dto : orderMenuDtos) {
            orderMenus.add(orderMenuService.create(new OrderMenu(order, menuService.show(dto
                    .getMenuItem().getMenu()
                    .getId()), dto.getQuantity())));
        }

        order.setOrderMenus(orderMenus);
        order.setSubTotal(order.getSubTotalOrderPrice());
        order.setTax(order.getTax());
        order.setTotalPrice(order.getTotalOrderPrice());
        order.setSpecialNotes(orderDto.getSpecialNote() != null ? orderDto.getSpecialNote() : "");
        order.setDeliveryTime(orderDto.getDeliveryAt());
        order.setDeliveryLocation(orderDto.getDeliveryLocation());

        this.orderService.update(order);

        String uri = ServletUriComponentsBuilder
                .fromCurrentServletMapping()
                .path("/orders/{id}")
                .buildAndExpand(order.getId())
                .toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(order, headers, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public Order getOrder(@PathVariable long id){
        return orderService.getOrder(id);
    }

    @PostMapping("/schedule")
    public OrderDto scheduleOrder(@RequestBody OrderDto orderDto){
        return null;
    }
    @PutMapping("/cancel")
    public OrderDto cancelOrder(@PathVariable long id){
        return null;
    }
    @PutMapping("/reject")
    public OrderDto rejectOrder(@PathVariable long id){
        return null;
    }

    @PutMapping("/accept")
    public OrderDto acceptOrder(@PathVariable long id){
        return null;
    }
    @GetMapping("/customers/{id}")
    public List<OrderDto> getCustomerOrders(@PathVariable long id){
        return null;
    }

    @GetMapping("{id}/byStatus")
    public Page<Order> getOrderByStatus(@PathVariable long id, OrderStatus orderStatus){
        return orderService.getOrderByStatus(id,orderStatus);
    }

    private void validateProductsExistence(List<OrderMenuDto> orderProducts) {
        List<OrderMenuDto> list = orderProducts
                .stream()
                .filter(om -> Objects.isNull(menuService.show(om
                        .getMenuItem().getMenu()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(list)) {
            throw new EntityNotFoundException("Menu not found");
        }
    }
}
