package com.eshi.addis.order;

import com.eshi.addis.order.OrderStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, Character> {
    @Override
    public Character convertToDatabaseColumn(OrderStatus orderStatus) {
        if (orderStatus ==null) {
            return null;
        }
        return orderStatus.getStatus();
    }

    @Override
    public OrderStatus convertToEntityAttribute(Character character) {
        if (character==null) {
            return null;
        }
        return Stream.of(OrderStatus.values())
                .filter(c -> c.getStatus()==character)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
