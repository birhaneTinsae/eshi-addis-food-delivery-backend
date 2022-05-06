package com.eshi.addis.order;

import com.eshi.addis.dto.OrderDTO;
import com.eshi.addis.restaurant.RestaurantDto;
import com.google.maps.errors.ApiException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

public interface OrderAPI {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    OrderDTO createOrder(@RequestBody() OrderDTO orderDTO) throws IOException, InterruptedException, ApiException;

    @PutMapping("/{orderId}")
    OrderDTO updateOrder(@PathVariable("orderId") String orderId, @RequestBody() OrderDTO orderDTO);

    @GetMapping("/{orderId}")
    OrderDTO getOrder(@PathVariable("orderId") String orderId);

    @PostMapping("/accept/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void acceptOrder(@PathVariable("orderId") String orderId);

    @PostMapping("/cancel/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void cancelOrder(@PathVariable("orderId") String orderId);

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteOrder(@PathVariable("orderId") String orderId);


    @GetMapping("/customer-orders/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<RestaurantDto>> getCustomerOrders(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                             @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable("customerId") String customerId);

    @GetMapping("/restaurant-orders/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<RestaurantDto>> getRestaurantOrders(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                             @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable("restaurantId") String restaurantId);
}
