package com.eshi.addis.exception;

import lombok.Data;

@Data
public class PasswordMisMatchException extends RuntimeException {
    public PasswordMisMatchException(String message) {
        super(message);
    }
}
