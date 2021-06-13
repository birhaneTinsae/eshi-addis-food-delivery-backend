package com.eshi.addis.security.privilage;

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

public interface PrivilegeAPI {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    PrivilegeDTO createPrivilege(@RequestBody() @Valid()PrivilegeDTO privilege);

    @GetMapping("/{privilegeId}")
    @ResponseStatus(HttpStatus.OK)
    PrivilegeDTO getPrivilege(@PathVariable("privilegeId") long privilegeId);

    @PutMapping("/{privilegeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    PrivilegeDTO updatePrivilege(@PathVariable("privilegeId") long privilegeId,@Valid() @RequestBody()  PrivilegeDTO privilege);

    @DeleteMapping("/{privilegeId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deletePrivilege(@PathVariable("privilegeId") long privilegeId);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<PrivilegeDTO>> getNearbyRestaurants(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                   @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
    );

}
