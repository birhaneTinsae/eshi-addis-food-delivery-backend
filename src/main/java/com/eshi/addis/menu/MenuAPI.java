package com.eshi.addis.menu;

import com.eshi.addis.dto.CategoryDTO;
import com.eshi.addis.dto.MenuDTO;
import com.eshi.addis.menu.modifier.MenuModifier;
import com.eshi.addis.menu.modifier.MenuModifierDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

public interface MenuAPI {
    @PostMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.CREATED)
    MenuDTO createMenu(@PathVariable long categoryId, @RequestBody MenuDTO menu);

    @GetMapping("/{menuId}")
    MenuDTO getMenu(@PathVariable long menuId);

    @PutMapping("/{menuId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    MenuDTO updateMenu(@PathVariable long menuId, @RequestBody MenuDTO menu);

    @DeleteMapping("/{menuId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteMenu(@PathVariable long menuId);


    @GetMapping("/category/{categoryId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MenuDTO>> getMenuByCategory(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                          @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable long categoryId
    );

    @GetMapping("/restaurant/{restaurantId}")
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MenuDTO>> getMenuByRestaurant(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                            @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable String restaurantId
    );

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<MenuDTO>> getMenus(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                 @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response

    );

    @PostMapping("/{menuId}/modifier")
    Iterable<MenuModifier> addModifier(@PathVariable("menuId") long menuId, List<MenuModifierDTO> modifier);

    @DeleteMapping("/{menuId}/modifier/{modifierId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteModifier(@PathVariable("menuId") long menuId, @PathVariable("modifierId") long modifierId);

}
