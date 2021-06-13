package com.eshi.addis.security.privilage;


import com.eshi.addis.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrivilegeServiceImp implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;

    @Override
    public Privilege createPrivilege(Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    @Override
    public Privilege getPrivilege(long privilegeId) {
        return privilegeRepository.findById(privilegeId).orElseThrow(() -> new EntityNotFoundException(Privilege.class, "Id", String.valueOf(privilegeId)));
    }

    @Override
    public Privilege updatePrivilege(long privilegeId, Privilege privilege) {
        return null;
    }

    @Override
    public void deletePrivilege(long privilegeId) {
        privilegeRepository.deleteById(privilegeId);
    }

    @Override
    public Page<Privilege> getPrivileges(Pageable pageable) {
        return privilegeRepository.findAll(pageable);
    }

}
