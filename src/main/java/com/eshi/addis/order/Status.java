package com.eshi.addis.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
    ACTIVE('A'), PENDING('P'), DELETED('D');
    private final char status;
}
