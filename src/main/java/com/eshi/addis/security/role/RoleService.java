package com.eshi.addis.security.role;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    Role createRole(Role role);

    Role getRole(long roleId);

    Role updateRole(long roleId, Role role);

    void deleteRole(long roleId);

    Role getRoleByName(String roleName);

    Page<Role> getRoles(Pageable pageable);
}
