package com.eshi.addis.order;

import com.eshi.addis.customer.Customer;
import com.eshi.addis.model.Address;
import com.eshi.addis.order.orderMenu.OrderMenu;
import com.eshi.addis.restaurant.Restaurant;
import com.eshi.addis.utils.Auditable;
import com.eshi.addis.utils.StringPrefixedSequenceGenerator;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "orders")
public class Order extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @GenericGenerator(
            name = "order_seq",
            strategy = "com.eshi.addis.utils.StringPrefixedSequenceGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "EAO_"),
                    @Parameter(name = StringPrefixedSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;
    //    @JsonFormat(pattern = "dd/MM/yyyy")
//    private LocalDate dateCreated;
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "pk.order")
    @Valid
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<OrderMenu> orderMenus = new ArrayList<>();

    private Double totalPrice;
    private Double tax;
    private Double subTotal;
    private String specialNotes;
    private LocalDateTime deliveryTime;
    @Embedded
    private Address deliveryAddress;
    private DeliveryType deliveryType;
    //    @Embedded
    //private OrderDistance orderDistance;
    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Customer customer;


    @Transient
    public Double getSubTotalOrderPrice() {
        var sum = 0D;
        List<OrderMenu> orderProducts = getOrderMenus();
        for (OrderMenu om : orderProducts) {
            sum += om.getTotalPrice();
        }

        return sum;
    }

    @Transient
    public Double getTax() {
        return getSubTotalOrderPrice() * 0.15;
    }

    @Transient
    public Double getTotalOrderPrice() {
        return getTax() + getSubTotalOrderPrice();
    }

    @Transient
    public int getNumberOfMenus() {
        return this.orderMenus.size();
    }

}

