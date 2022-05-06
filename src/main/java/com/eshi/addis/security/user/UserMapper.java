package com.eshi.addis.security.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toUserResponseDTO(User user);

    User toUser(UserResponseDTO userResponseDTO);

    User toUser(UserDTO userDTO);
}
