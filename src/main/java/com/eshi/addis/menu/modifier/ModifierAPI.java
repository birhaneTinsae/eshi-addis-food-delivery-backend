package com.eshi.addis.menu.modifier;

import com.eshi.addis.favourite.FavouriteDTO;
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

public interface ModifierAPI {
    @PostMapping("/{restaurantId}")
    @ResponseStatus(HttpStatus.CREATED)
    ModifierDTO createModifier(@PathVariable String restaurantId, @RequestBody ModifierDTO modifier);

    @GetMapping("/{modifierId}")
    @ResponseStatus(HttpStatus.OK)
    ModifierDTO getModifier(@PathVariable long modifierId);

    @DeleteMapping("/{modifierId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteModifier(@PathVariable long modifierId);

    @GetMapping("/{restaurantId}")
    ResponseEntity<PagedModel<ModifierDTO>> getModifiersByRestaurant(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                     @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable("restaurantId") String restaurantId);
}
