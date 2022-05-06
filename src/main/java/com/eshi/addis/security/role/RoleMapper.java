package com.eshi.addis.security.role;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleDTO roleDTO);

    RoleDTO toRoleDTO(Role role);
}
