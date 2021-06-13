package com.eshi.addis.security.user;


import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static com.eshi.addis.utils.Util.dtoMapper;


@RestController
@RequestMapping("users")
@Log4j2
@RequiredArgsConstructor
public class UserController implements UserAPI {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UserResponseDTO createUser(UserDTO user) {
        return dtoMapper(userService.createUser(dtoMapper(user, User.class, modelMapper)), UserResponseDTO.class, modelMapper);
    }

    @Override
    public UserResponseDTO updateUser(long userId, UserDTO user) {
        return dtoMapper(userService.updateUser(userId, dtoMapper(user, User.class, modelMapper)), UserResponseDTO.class, modelMapper);

    }

    @Override
    public UserResponseDTO getUser(long userId) {
        return dtoMapper(userService.getUser(userId), UserResponseDTO.class, modelMapper);

    }

    @Override
    public void deleteUser(long userId) {
        userService.deleteUser(userId);
    }

    @Override
    public UserResponseDTO resetPassword(long userId, UserPasswordDTO passwordReset) {
        return dtoMapper(userService.resetPassword(userId, passwordReset), UserResponseDTO.class, modelMapper);
    }

    @Override
    public ResponseEntity<PagedModel<UserResponseDTO>> getRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                UserResponseDTO.class, uriBuilder, response, pageable.getPageNumber(), userService.getUsers(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<UserResponseDTO>>(assembler.toModel(userService.getUsers(pageable).map(u -> dtoMapper(u, UserResponseDTO.class, modelMapper))), HttpStatus.OK);

    }


}