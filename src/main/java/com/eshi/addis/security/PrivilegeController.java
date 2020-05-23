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
@RequestMapping("privileges")
@Tag(name = "Privilege", description = "Privilege API")
public class PrivilegeController implements Common<Privilege, Privilege> {
    private PrivilegeService privilegeService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public PrivilegeController(PrivilegeService privilegeService) {
        this.privilegeService = privilegeService;
    }

    @Operation(summary = "Create new Privilege",
            description = "This API creates new Privilege",
            tags = {"Privilege"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Privilege created",
                    content = @Content(schema = @Schema(implementation = Privilege.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Privilege already exists")})
    @Override
    public Privilege store(@Parameter(description = "Privilege to add. Cannot null or empty.",
            required = true, schema = @Schema(implementation = Privilege.class))
                               @Valid @RequestBody Privilege privilege) {
        return privilegeService.store(privilege);
    }
    @Operation(summary = "Find Privilege by ID",
            description = "Returns a single Privilege",
            tags = {"Privilege"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = Privilege.class))),
            @ApiResponse(responseCode = "404", description = "Privilege not found")})
    @Override
    public Iterable<Privilege> store(List<Privilege> t) {
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
    public Privilege show(@Parameter(description = "Id of the Privilege to be obtained. Cannot be empty.", required = true) long id) {
        return privilegeService.show(id);
    }
    @Operation(summary = "Update an existing Privilege", description = "", tags = {"Privilege"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Contact not found"),
            @ApiResponse(responseCode = "405", description = "Validation exception")})

    @Override
    public Privilege update(@Parameter(description = "Id of the Privilege to be update. Cannot be empty.",
            required = true) @PathVariable long id, @Valid @RequestBody Privilege privilege) {
        return null;
    }
    @Operation(summary = "Deletes a Privilege", description = "", tags = {"Privilege"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "404", description = "Privilege not found")})
    @Override
    public boolean delete(@Parameter(description = "Id of the Privilege to be obtained. Cannot be empty.", required = true) @PathVariable long id) {
        return privilegeService.delete(id);
    }
@GetMapping
    @Operation(summary = "Find all Privilege", description = "Name search by %name% format", tags = {"Privilege"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Privilege.class))))})
    public ResponseEntity<PagedModel<Privilege>> getAll(@Parameter(description = "pagination object",
            required = false, schema = @Schema(implementation = Pageable.class))
                                                     @Valid Pageable pageable, Sort sort
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                Privilege.class, uriBuilder, response, pageable.getPageNumber(), privilegeService.getAll(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<Privilege>>(assembler.toModel(privilegeService.getAll(pageable)), HttpStatus.OK);

    }

    @Override
    public Iterable<Privilege> getAll(Pageable pageable) {
        return privilegeService.getAll(pageable);
    }
}
