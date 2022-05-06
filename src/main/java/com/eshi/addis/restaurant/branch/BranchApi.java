package com.eshi.addis.restaurant.branch;

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

public interface BranchApi {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{restaurantId}")
    BranchDto createBranch(@PathVariable String restaurantId, @RequestBody BranchDto Branch);

    @GetMapping("/{branchId}")
    BranchDto getBranch(@PathVariable long branchId);

    @PutMapping("/{branchId}")
    BranchDto updateBranch(@PathVariable long branchId, @RequestBody BranchDto Branch);

    @DeleteMapping("/{branchId}")
    void deleteBranch(@PathVariable long branchId);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<BranchDto>> getBranches(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                        @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
    );
}
