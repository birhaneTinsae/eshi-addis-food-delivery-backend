package com.eshi.addis.security.role;


import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.security.privilage.Privilege;
import com.eshi.addis.security.privilage.PrivilegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class RoleServiceImp implements RoleService {
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;

    @Override
    public Role createRole(Role role) {
        if (isRoleExistsByName(role.getName())) {
            throw new EntityExistsException("Role with name '" + role.getName() + "' already exists");
        }
        getRolePrivileges(role, role);
        return roleRepository.save(role);
    }

    @Override
    public Role getRole(long roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new EntityNotFoundException(Role.class, " id ", String.valueOf(roleId)));
    }

    @Override
    public Role updateRole(long roleId, Role role) {
        var r = getRole(roleId);
        if (!isNull(role.getPrivileges()) && !role.getPrivileges().isEmpty()) {
            getRolePrivileges(role, r);
        }

        return roleRepository.save(r);
    }

    @Override
    public void deleteRole(long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public Page<Role> getRoles(Pageable pageable) {
        return roleRepository.findAllByDeletedAtIsNull(pageable);
    }


    private void getRolePrivileges(Role role, Role r) {
        Set<Privilege> privileges = new HashSet<>();
        for (Privilege privilege : role.getPrivileges()) {
            Privilege byName = privilegeRepository.findByName(privilege.getName())
                    .orElseThrow(() -> new EntityNotFoundException(Privilege.class, " name ", privilege.getName()));

            privileges.add(byName);
        }
        r.setPrivileges(privileges);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException(Role.class, "Name", name));
    }

    private boolean isRoleExistsByName(String roleName) {
        return roleRepository.existsByName(roleName);
    }
}
