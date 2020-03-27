package com.eshi.addis.model;

public enum Status {
    SENT('S'), PREPARING('P'), CANCELED('C'), DELIVERED('D');
    private char status;

    Status(char status) {
        this.status = status;
    }

    public char getStatus() {
        return status;
    }
}
