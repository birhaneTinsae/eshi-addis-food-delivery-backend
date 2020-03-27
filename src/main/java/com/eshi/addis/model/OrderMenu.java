package com.eshi.addis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Data
//@EqualsAndHashCode
public class OrderMenu implements Serializable {

    @EmbeddedId
    @JsonIgnore
    private OrderMenuPK pk;

    @Column(nullable = false) private Integer quantity;

    public OrderMenu() {
        super();
    }

    public OrderMenu(Order order, Menu menu, Integer quantity) {
        pk = new OrderMenuPK();
        pk.setOrder(order);
        pk.setMenu(menu);
        this.quantity = quantity;
    }

  //  @Transient
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Menu getMenu() {
        return this.pk.getMenu();
    }

    @Transient
    public Double getTotalPrice() {
        return getMenu().getPrice() * getQuantity();
    }

    public OrderMenuPK getPk() {
        return pk;
    }

    public void setPk(OrderMenuPK pk) {
        this.pk = pk;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
