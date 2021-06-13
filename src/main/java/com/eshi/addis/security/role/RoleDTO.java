package com.eshi.addis.security.role;

import com.eshi.addis.security.privilage.PrivilegeDTO;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {
    private long id;
    private String name;
    private List<PrivilegeDTO> privileges;
}
