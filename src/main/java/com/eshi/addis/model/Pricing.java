package com.eshi.addis.model;

public enum Pricing {
    LOW('L'), MEDIUM('M'), HEIGH('H');
    private char scale;

    Pricing(char scale) {
        this.scale = scale;
    }

    public char getScale() {
        return scale;
    }
}
