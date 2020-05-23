package com.eshi.addis.security;

import com.enatsystem.hr.utils.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements Common<Role, Role> {
    private RoleRepository roleRepository;
    private PrivilegeRepository privilegeRepository;

    public RoleService(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        this.roleRepository = roleRepository;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Role store(Role role) {
        //  role.getPrivileges().forEach(p->p.setRoles(Arrays.asList(role)));
        if (roleRepository.findByName(role.getName()).isPresent()) {
            throw new EntityExistsException("Role with name '" + role.getName() + "' already exists");
        }
        ArrayList<Privilege> privileges = new ArrayList<>();
        for (Privilege privilege : role.getPrivileges()) {
            Privilege byName = privilegeRepository.findByName(privilege.getName()).orElseThrow(()->new EntityNotFoundException("Privilege with name '" + privilege.getName() + "' not exists"));

            privileges.add(byName);
        }
        role.setPrivileges(privileges);

        return roleRepository.save(role);
    }

    @Override
    public Iterable<Role> store(List<Role> t) {
        return null;
    }

    @Override
    public Role show(long id) {
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found"));
    }

    @Override
    public Role update(long id, Role role) {
        Role r = show(id);

        if (role.getPrivileges() != null && role.getPrivileges().size() != 0) {
//            r.setPrivileges(role.getPrivileges());
            ArrayList<Privilege> privileges = new ArrayList<>();
            for (Privilege privilege : role.getPrivileges()) {
                Privilege byName = privilegeRepository.findByName(privilege.getName()).orElseThrow(()->new EntityNotFoundException("Privilege with name '" + privilege.getName() + "' not exists"));

                privileges.add(byName);
            }
            r.setPrivileges(privileges);
        }

        return roleRepository.save(r);

    }

    @Override
    public boolean delete(long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found"));
        role.setDeletedAt(LocalDateTime.now());
        roleRepository.save(role);
        return true;
    }

    @Override
    public Page<Role> getAll(Pageable pageable) {
       // pageable.getSortOr(sort);
        return roleRepository.findAllByDeletedAtNull(pageable);
    }
}
