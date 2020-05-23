package com.eshi.addis.user;


import com.enatsystem.hr.dto.UserDto;
import com.enatsystem.hr.dto.UserPasswordDto;
import com.enatsystem.hr.utils.PaginatedResultsRetrievedEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("users")
@Log4j2
public class UserController {

    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create User", security = @SecurityRequirement(name = "basicAuth"))
    @PostMapping()
    public User createUser(@RequestBody UserDto user) {
        log.info(user.toString());
        return userService.registerNewUserAccount(user);
    }

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "basicAuth"))
    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "basicAuth"))
    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody UserDto user) {
        return userService.update(id, user);
    }

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "basicAuth"))
    @GetMapping("/authorities")
    public/* java.util.Collection<? extends org.springframework.security.core.GrantedAuthority>*/User getUser(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }

    //    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "basicAuth"))
//    @GetMapping
//    public Iterable<User> getUsers(){
//        return userService.getUsers();
//    }
    @Operation(summary = "Find all Users", security = @SecurityRequirement(name = "basicAuth"), description = "Return all user with paging", tags = {"Users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))})
    @GetMapping()
    public ResponseEntity<PagedModel<User>> getAll(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                   @Valid  Pageable pageable, Sort sort
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                User.class, uriBuilder, response, pageable.getPageNumber(), userService.getAll(pageable, sort).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<User>>(assembler.toModel(userService.getAll(pageable, sort)), HttpStatus.OK);

    }

    @Operation(summary = "My endpoint", security = @SecurityRequirement(name = "basicAuth"))
    @PutMapping("/password-reset")
    public User passwordReset(@RequestBody UserPasswordDto user) {
        return userService.passwordRest(user);
    }

    @PostMapping("/opt")
    public void getOTP(){

    }
    @PostMapping("/confirm-opt")
    public void confirmOTP(){

    }
}