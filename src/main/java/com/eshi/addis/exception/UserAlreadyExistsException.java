package com.eshi.addis.exception;

import lombok.Data;

@Data
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
