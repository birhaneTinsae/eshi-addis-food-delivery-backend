package com.eshi.addis.security;

import com.enatsystem.hr.utils.Common;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PrivilegeService implements Common<Privilege,Privilege> {
private PrivilegeRepository privilegeRepository;

    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public Privilege store(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public Iterable<Privilege> store(List<Privilege> t) {
        return null;
    }

    @Override
    public Privilege show(long id) {
        return privilegeRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
    }

    @Override
    public Privilege update(long id,Privilege privilege) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Page<Privilege> getAll(Pageable pageable) {
    //    pageable.getSortOr(sort);
        return privilegeRepository.findAllByDeletedAtNull(pageable);
    }
}
