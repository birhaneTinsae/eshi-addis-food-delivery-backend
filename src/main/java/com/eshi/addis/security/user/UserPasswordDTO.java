package com.eshi.addis.security.user;

import com.eshi.addis.validation.ValidPassword;
import lombok.Data;

@Data
public class UserPasswordDTO {
    private String username;
    private String oldPassword;
    @ValidPassword
    private String newPassword;
}
