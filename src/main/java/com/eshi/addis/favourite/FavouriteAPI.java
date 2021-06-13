package com.eshi.addis.favourite;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface FavouriteAPI {
    @ResponseStatus(HttpStatus.CREATED)
    FavouriteDTO createFavourite(@RequestBody() Favourite favourite);

    @ResponseStatus(HttpStatus.ACCEPTED)
    void removeFavourite(@PathVariable("id") long id);

    ResponseEntity<PagedModel<FavouriteDTO>> getCustomerFavourites(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                   @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
            , @PathVariable("customerId") String customerId);
}
