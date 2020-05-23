package com.eshi.addis.security;


import com.enatsystem.hr.utils.Common;
import com.enatsystem.hr.utils.PaginatedResultsRetrievedEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.List;

@RestController
@RequestMapping("roles")
@Tag(name = "Role", description = "Role API")
public class RoleController implements Common<Role,Role> {

    private RoleService roleService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    
    @Operation(summary = "Create new Role",
            description = "This API creates new Role",
            tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created",
                    content = @Content(schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Role already exists")})
    @Override
    public Role store(@Parameter(description = "Role to add. Cannot null or empty.",
            required = true, schema = @Schema(implementation = Role.class))
                          @Valid @RequestBody  Role role) {
        return roleService.store(role);
    }

    @Override
    public Iterable<Role> store(List<Role> t) {
        return null;
    }
    @Operation(summary = "Find Role by ID",
            description = "Returns a single Role",
            tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "404", description = "Role not found")})
    @Override
    public Role show( @Parameter(description = "Id of the Role to be obtained. Cannot be empty.", required = true)
                               @PathVariable long id) {
        return roleService.show(id);
    }
    @Operation(summary = "Update an existing Role", description = "", tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Contact not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})

    @Override
    public Role update( @Parameter(description = "Id of the Role to be update. Cannot be empty.",
            required = true)
                               @PathVariable     long id,
                        @Parameter(description = "Role to update. Cannot null or empty.",
                                required = true, schema = @Schema(implementation = Role.class))
                            @Valid @RequestBody Role role) {
        return roleService.update(id,role);
    }
    @Operation(summary = "Deletes a Role", description = "", tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Role not found")})
    @Override
    public boolean delete( @Parameter(description = "Id of the Role to be obtained. Cannot be empty.", required = true)  @PathVariable long id) {
        return roleService.delete(id);
    }

    @Operation(summary = "Find all Role", description = "Name search by %name% format", tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Role.class))))})
    @GetMapping()
    public ResponseEntity<PagedModel<Role>> getAll(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                  @Valid Pageable pageable, Sort sort
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                Role.class, uriBuilder, response, pageable.getPageNumber(), roleService.getAll(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<Role>>(assembler.toModel(roleService.getAll(pageable)), HttpStatus.OK);

    }
    @Operation(summary = "Find all Role", description = "Name search by %name% format", tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Role.class))))})

    @Override
    public Iterable<Role> getAll(Pageable pageable) {
        return roleService.getAll(pageable);
    }
}
