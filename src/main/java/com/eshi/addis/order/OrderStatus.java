package com.eshi.addis.order;

public enum OrderStatus {
    SENT('S'), PREPARING('P'), CANCELED('C'), DELIVERED('D');
    private char status;

    OrderStatus(char status) {
        this.status = status;
    }

    public char getStatus() {
        return status;
    }
}
