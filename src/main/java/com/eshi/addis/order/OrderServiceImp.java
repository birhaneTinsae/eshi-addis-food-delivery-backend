package com.eshi.addis.order;

import com.eshi.addis.coupon.Coupon;
import com.eshi.addis.coupon.CouponService;
import com.eshi.addis.dto.OrderDTO;
import com.eshi.addis.dto.OrderMenuDTO;
import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.menu.MenuService;
import com.eshi.addis.order.orderMenu.OrderMenu;
import com.eshi.addis.order.orderMenu.OrderMenuService;
import com.eshi.addis.restaurant.RestaurantService;
import com.google.maps.GeoApiContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMenuService orderMenuService;
    private final MenuService menuService;
    private final GeoApiContext geoApiContext;
    private final RestaurantService restaurantService;
    private final CouponService couponService;

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Coupon coupon = null;
        var restaurant = restaurantService.getRestaurant(orderDTO.getRestaurant().getId());

        List<OrderMenuDTO> orderMenus = orderDTO.getMenus();
        var order = new Order();
        order.setOrderStatus(OrderStatus.SENT);
        order = orderRepository.save(order);

        List<OrderMenu> menus = new ArrayList<>();
        validateProductsExistence(orderMenus);
        for (OrderMenuDTO dto : orderMenus) {
            menus.add(orderMenuService.store(new OrderMenu(order
                    , dto.getMenuItem().getMenu()
                    , dto.getQuantity())));
        }

        order.setOrderMenus(menus);
        order.setRestaurant(restaurant);
//       var distanceMatrix = getDistance(orderDto
//                        .getDeliveryLocation()
//                        .getLocation()
//                , restaurant
//                        .getAddress()
//                        .getLocation());
        //  order.setOrderDistance(distanceMatrix.rows[0]);
        if (!isNull(orderDTO.getCoupon())) {
            coupon = couponService.getCoupon(orderDTO.getCoupon().getId());
            order.setSubTotal(order.getSubTotalOrderPrice() - (coupon.getAmount() * order.getSubTotalOrderPrice()));
        } else {
            order.setSubTotal(order.getSubTotalOrderPrice());
        }
        order.setTax(order.getTax());
        order.setTotalPrice(order.getTotalOrderPrice());
        order.setSpecialNotes(orderDTO.getSpecialNote() != null ? orderDTO.getSpecialNote() : "");
        order.setDeliveryTime(orderDTO.getDeliveryAt() != null ? orderDTO.getDeliveryAt() : LocalDateTime.now());
        order.setDeliveryAddress(orderDTO.getDeliveryLocation());

        return orderRepository.save(order);

    }

    @Override
    public Order updateOrder(String orderId, OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Order getOrder(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException(Order.class, "id"));
    }

    @Override
    public void acceptOrder(String orderId) {
        var order = getOrder(orderId);
        order.setOrderStatus(OrderStatus.ACCEPTED);
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(String orderId) {
        var order = getOrder(orderId);
        order.setOrderStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }

    @Override
    public Page<Order> getCustomerOrders(String customerId, Pageable pageable) {
        return orderRepository.findAllByCustomer_Id(customerId, pageable);
    }

    @Override
    public Page<Order> getRestaurantOrders(String restaurantId, Pageable pageable) {
        return orderRepository.findAllByRestaurant_Id(restaurantId, pageable);
    }


//    public DirectionsResult getDirection(Point origin, Point destination) throws InterruptedException, ApiException, IOException {
//        return DirectionsApi.newRequest(geoApiContext)
//                .origin(new LatLng(origin.getX(), origin.getY()))
//                .destination(new LatLng(destination.getX(), destination.getY()))
//                .mode(TravelMode.DRIVING)
//                .optimizeWaypoints(true)
//                .await();
//    }
//
//    public DistanceMatrix getDistance(Point origin, Point destination) throws InterruptedException, ApiException, IOException {
//        return DistanceMatrixApi.newRequest(geoApiContext)
//                .origins(new LatLng(origin.getX(), origin.getY()))
//                .destinations(new LatLng(destination.getX(), destination.getY()))
//                .mode(TravelMode.DRIVING)
//                .units(Unit.METRIC)
//                .await();
//    }

    private void validateProductsExistence(List<OrderMenuDTO> orderProducts) {
        List<OrderMenuDTO> orderMenus = orderProducts
                .stream()
                .filter(om -> Objects.isNull(menuService.getMenu(om
                        .getMenuItem().getMenu()
                        .getId())))
                .collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(orderMenus)) {
            throw new javax.persistence.EntityNotFoundException("Menu not found");
        }
    }
}
