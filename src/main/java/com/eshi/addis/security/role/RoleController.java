package com.eshi.addis.security.role;


import com.eshi.addis.security.IsAdmin;
import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

import static com.eshi.addis.utils.Util.dtoMapper;


@RestController
@RequestMapping("roles")
@Tag(name = "Role", description = "Role API")
@RequiredArgsConstructor
public class RoleController implements RoleAPI {

    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @IsAdmin
    @Operation(summary = "Create new Role",
            description = "This API creates new Role",
            tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role created",
                    content = @Content(schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Role already exists")})
    @Override
    public RoleDTO createRole(RoleDTO role) {
        return dtoMapper(roleService.createRole(dtoMapper(role, Role.class, modelMapper)), RoleDTO.class, modelMapper);
    }

    @IsAdmin
    @Operation(summary = "Find Role by ID",
            description = "Returns a single Role",
            tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "404", description = "Role not found")})
    @Override
    public RoleDTO getRole(long roleId) {
        return dtoMapper(roleService.getRole(roleId), RoleDTO.class, modelMapper);
    }

    @IsAdmin
    @Operation(summary = "Update an existing Role", description = "", tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Contact not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})

    @Override
    public RoleDTO updateRole(long roleId, RoleDTO role) {
        return dtoMapper(roleService.updateRole(roleId, dtoMapper(role, Role.class, modelMapper)), RoleDTO.class, modelMapper);
    }

    @IsAdmin
    @Operation(summary = "Deletes a Role", description = "", tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Role not found")})
    @Override
    public void deleteRole(long roleId) {
        roleService.deleteRole(roleId);
    }

    @IsAdmin
    @Operation(summary = "Find all Role", description = "Name search by %name% format", tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Role.class))))})
    @Override
    public ResponseEntity<PagedModel<RoleDTO>> getNearbyRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                Role.class, uriBuilder, response, pageable.getPageNumber(), roleService.getRoles(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RoleDTO>>(assembler.toModel(roleService.getRoles(pageable).map(r -> dtoMapper(r, RoleDTO.class, modelMapper))), HttpStatus.OK);

    }
}
