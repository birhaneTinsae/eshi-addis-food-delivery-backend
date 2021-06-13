package com.eshi.addis.model;

import lombok.Getter;

@Getter
public enum Pricing {
    LOW('L'), MEDIUM('M'), HIGH('H');
    private char scale;
    Pricing(char scale) {
        this.scale = scale;
    }
}
