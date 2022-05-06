package com.eshi.addis.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    SENT('S'),ACCEPTED('A'), PREPARING('P'), CANCELED('C'), DELIVERED('D'),PAID('P');
    private final char status;

}
