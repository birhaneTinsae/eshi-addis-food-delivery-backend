package com.eshi.addis.security.user;

import com.eshi.addis.security.role.RoleDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserResponseDTO implements Serializable {
    private long id;
    private String username;
    private String fullName;
    private List<RoleDTO> roles;
    private String email;
    private boolean enabled;
    private boolean active;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean firstLogin;


}
