package com.eshi.addis.dto;


import com.eshi.addis.customer.Customer;
import com.eshi.addis.security.role.Role;
import lombok.Data;

import java.util.Collection;

@Data
public class UserDTO {
    private String fullName;
    private String username;
    private String password;
    private String confirmPassword;
    private Collection<Role> roles;
    //    @Email
//    private String email;
    private boolean enabled;
    private boolean active;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean firstLogin;

    private Customer customer;
}
