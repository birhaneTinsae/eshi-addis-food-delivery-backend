package com.eshi.addis.security.user;

import com.eshi.addis.dto.RestaurantDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface UserAPI {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    UserResponseDTO createUser(@Valid() @RequestBody() UserDTO user);

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    UserResponseDTO updateUser(@PathVariable("userId") long userId,@Valid() @RequestBody() UserDTO user);

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    UserResponseDTO getUser(@PathVariable("userId") long userId);

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteUser(@PathVariable("userId") long userId);

    @PutMapping("/password-reset/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    UserResponseDTO resetPassword(@PathVariable("userId") long userId,@Valid() @RequestBody() UserPasswordDTO passwordReset);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<UserResponseDTO>> getRestaurants(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                       @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
    );
}
