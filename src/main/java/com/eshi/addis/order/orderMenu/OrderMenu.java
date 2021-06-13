package com.eshi.addis.order.orderMenu;

import com.eshi.addis.menu.Menu;
import com.eshi.addis.order.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class OrderMenu implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private OrderMenuPK pk;

    @Column(nullable = false)
    private Integer quantity;


    public OrderMenu(Order order, Menu menu, Integer quantity) {
        pk = new OrderMenuPK();
        pk.setOrder(order);
        pk.setMenu(menu);
        this.quantity = quantity;
    }

    @Transient
    public Double getTotalPrice() {
        return this.pk.getMenu().getPrice() * getQuantity();
    }


}
