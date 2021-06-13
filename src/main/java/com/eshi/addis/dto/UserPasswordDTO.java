package com.eshi.addis.dto;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private long id;
    private String username;
    private String oldPassword;
    private String newPassword;
}
