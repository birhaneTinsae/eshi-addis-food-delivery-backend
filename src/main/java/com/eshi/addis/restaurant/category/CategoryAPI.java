package com.eshi.addis.restaurant.category;

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

public interface CategoryAPI {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{restaurantId}")
    CategoryDto createCategory(@PathVariable String restaurantId, @RequestBody CategoryDto category);

    @GetMapping("/{categoryId}")
    CategoryDto getCategory(@PathVariable long categoryId);

    @PutMapping("/{categoryId}")
    CategoryDto updateCategory(@PathVariable long categoryId, @RequestBody CategoryDto category);

    @DeleteMapping("/{categoryId}")
    void deleteCategory(@PathVariable long categoryId);

    @GetMapping("/restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<CategoryDto>> getRestaurantMenuCategories(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                        @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable String restaurantId
    );

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<CategoryDto>> getCategory(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                        @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
    );
}
