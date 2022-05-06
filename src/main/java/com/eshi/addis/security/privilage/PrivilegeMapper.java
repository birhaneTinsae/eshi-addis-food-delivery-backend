package com.eshi.addis.security.privilage;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrivilegeMapper {
    Privilege toPrivilege(PrivilegeDTO privilegeDTO);

    PrivilegeDTO privilegeDTO(Privilege privilege);
}
