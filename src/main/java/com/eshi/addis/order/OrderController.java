package com.eshi.addis.order;

import com.eshi.addis.dto.OrderDTO;
import com.eshi.addis.dto.RestaurantDTO;
import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

import static com.eshi.addis.utils.Util.dtoMapper;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController implements OrderAPI {
    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return dtoMapper(orderService.createOrder(orderDTO), OrderDTO.class, modelMapper);
    }

    @Override
    public OrderDTO updateOrder(String orderId, OrderDTO orderDTO) {
        return dtoMapper(orderService.updateOrder(orderId, orderDTO), OrderDTO.class, modelMapper);
    }

    @Override
    public OrderDTO getOrder(String orderId) {
        return dtoMapper(orderService.getOrder(orderId), OrderDTO.class, modelMapper);
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
    public ResponseEntity<PagedModel<RestaurantDTO>> getCustomerOrders(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String customerId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDTO.class, uriBuilder, response, pageable.getPageNumber(), orderService.getCustomerOrders(customerId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDTO>>(assembler.toModel(orderService.getCustomerOrders(customerId, pageable).map(order -> dtoMapper(order, OrderDTO.class, modelMapper))), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<RestaurantDTO>> getRestaurantOrders(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String restaurantId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RestaurantDTO.class, uriBuilder, response, pageable.getPageNumber(), orderService.getRestaurantOrders(restaurantId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RestaurantDTO>>(assembler.toModel(orderService.getRestaurantOrders(restaurantId, pageable).map(order -> dtoMapper(order, OrderDTO.class, modelMapper))), HttpStatus.OK);

    }
}
