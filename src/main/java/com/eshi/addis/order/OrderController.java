package com.eshi.addis.order;

import com.eshi.addis.dto.OrderDTO;
import com.eshi.addis.restaurant.RestaurantDto;
import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import com.google.maps.errors.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController implements OrderAPI {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) throws IOException, InterruptedException, ApiException {
        return orderMapper.toOrderDTO(orderService.createOrder(orderDTO));
    }

    @Override
    public OrderDTO updateOrder(String orderId, OrderDTO orderDTO) {
        return orderMapper.toOrderDTO(orderService.updateOrder(orderId, orderDTO));
    }

    @Override
    public OrderDTO getOrder(String orderId) {
        return orderMapper.toOrderDTO(orderService.getOrder(orderId));
    }

    @Override
    public void acceptOrder(String orderId) {
        orderService.acceptOrder(orderId);
    }

    @Override
    public void cancelOrder(String orderId) {
        orderService.cancelOrder(orderId);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderService.deleteOrder(orderId);
    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDto>> getCustomerOrders(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String customerId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDto.class, uriBuilder, response, pageable.getPageNumber(), orderService.getCustomerOrders(customerId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDto>>(assembler.toModel(orderService.getCustomerOrders(customerId, pageable).map(orderMapper::toOrderDTO)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDto>> getRestaurantOrders(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String restaurantId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDto.class, uriBuilder, response, pageable.getPageNumber(), orderService.getRestaurantOrders(restaurantId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDto>>(assembler.toModel(orderService.getRestaurantOrders(restaurantId, pageable).map(orderMapper::toOrderDTO)), HttpStatus.OK);

    }
}
