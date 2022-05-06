package com.eshi.addis.security.privilage;


import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("privileges")
@Tag(name = "Privilege", description = "Privilege API")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PrivilegeController implements PrivilegeAPI {
    private final PrivilegeService privilegeService;
    private final ApplicationEventPublisher eventPublisher;
    private final PrivilegeMapper privilegeMapper;

    @Operation(summary = "Create new Privilege",
            description = "This API creates new Privilege",
            tags = {"Privilege"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Privilege created",
                    content = @Content(schema = @Schema(implementation = Privilege.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Privilege already exists")})
    @Override
    public PrivilegeDTO createPrivilege(PrivilegeDTO privilege) {
        return privilegeMapper.privilegeDTO(privilegeService.createPrivilege(privilegeMapper.toPrivilege(privilege)));
    }

    @Operation(summary = "Find Privilege by ID",
            description = "Returns a single Privilege",
            tags = {"Role"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = PrivilegeDTO.class))),
            @ApiResponse(responseCode = "404", description = "Role not found")})
    @Override
    public PrivilegeDTO getPrivilege(long privilegeId) {
        return privilegeMapper.privilegeDTO(privilegeService.getPrivilege(privilegeId));

    }

    @Operation(summary = "Update an existing Privilege", description = "", tags = {"Privilege"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Contact not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})
    @Override
    public PrivilegeDTO updatePrivilege(long privilegeId, PrivilegeDTO privilege) {
        return null;
    }

    @Operation(summary = "Deletes a Privilege", description = "", tags = {"Privilege"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Privilege not found")})
    @Override
    public void deletePrivilege(long privilegeId) {
        privilegeService.deletePrivilege(privilegeId);
    }

    @GetMapping
    @Operation(summary = "Find all Privilege", description = "Name search by %name% format", tags = {"Privilege"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Privilege.class))))})

    @Override
    public ResponseEntity<PagedModel<PrivilegeDTO>> getNearbyRestaurants(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                PrivilegeDTO.class, uriBuilder, response, pageable.getPageNumber(), privilegeService.getPrivileges(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<PrivilegeDTO>>(assembler.toModel(privilegeService.getPrivileges(pageable).map(privilegeMapper::privilegeDTO)), HttpStatus.OK);

    }


}
