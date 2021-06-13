package com.eshi.addis.restaurant.category;

import com.eshi.addis.dto.CategoryDTO;
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

public interface CategoryAPI {

    @PostMapping("/{restaurantId}")
    CategoryDTO createCategory(@PathVariable String restaurantId, @RequestBody CategoryDTO category);

    @GetMapping("/{categoryId}")
    CategoryDTO getCategory(@PathVariable long categoryId);

    @PutMapping("/{categoryId}")
    CategoryDTO updateCategory(@PathVariable long categoryId, @RequestBody CategoryDTO category);

    @DeleteMapping("/{categoryId}")
    void deleteCategory(@PathVariable long categoryId);

    @GetMapping("/restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<CategoryDTO>> getRestaurantMenuCategories(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                        @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable String restaurantId
    );

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<CategoryDTO>> getCategory(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                        @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
    );
}
