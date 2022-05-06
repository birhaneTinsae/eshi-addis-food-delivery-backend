package com.eshi.addis.security.user;


import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("users")
@Log4j2
@RequiredArgsConstructor
public class UserController implements UserAPI {

    private final UserService userService;
    private final UserMapper userMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UserResponseDTO createUser(UserDTO user) {
        return userMapper.toUserResponseDTO(userService.createUser(userMapper.toUser(user)));
    }

    @Override
    public UserResponseDTO updateUser(long userId, UserDTO user) {
        return userMapper.toUserResponseDTO(userService.updateUser(userId, userMapper.toUser(user)));

    }

    @Override
    public UserResponseDTO getUser(long userId) {
        return userMapper.toUserResponseDTO(userService.getUser(userId));

    }

    @Override
    public void deleteUser(long userId) {
        userService.deleteUser(userId);
    }

    @Override
    public UserResponseDTO resetPassword(long userId, UserPasswordDTO passwordReset) {
        return userMapper.toUserResponseDTO(userService.resetPassword(userId, passwordReset));
    }

    @Override
    public ResponseEntity<PagedModel<UserResponseDTO>> getRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                UserResponseDTO.class, uriBuilder, response, pageable.getPageNumber(), userService.getUsers(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<UserResponseDTO>>(assembler.toModel(userService.getUsers(pageable).map(userMapper::toUserResponseDTO)), HttpStatus.OK);

    }


}