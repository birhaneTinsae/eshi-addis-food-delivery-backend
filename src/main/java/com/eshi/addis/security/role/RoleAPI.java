package com.eshi.addis.security.role;

import com.eshi.addis.security.privilage.PrivilegeDTO;
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

public interface RoleAPI {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    RoleDTO createRole(@RequestBody @Valid() RoleDTO role);

    @GetMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    RoleDTO getRole(@PathVariable long roleId);

    @PutMapping("/{roleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    RoleDTO updateRole(@PathVariable long roleId, @RequestBody @Valid() RoleDTO role);

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteRole(@PathVariable long roleId);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<RoleDTO>> getNearbyRestaurants(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                             @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
    );
}
