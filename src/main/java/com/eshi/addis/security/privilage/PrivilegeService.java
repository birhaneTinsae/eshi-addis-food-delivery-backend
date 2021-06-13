package com.eshi.addis.security.privilage;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrivilegeService {

    Privilege createPrivilege(Privilege privilege);

    Privilege getPrivilege(long privilegeId);

    Privilege updatePrivilege(long privilegeId, Privilege privilege);

    void deletePrivilege(long privilegeId);

    Page<Privilege> getPrivileges(Pageable pageable);

}
