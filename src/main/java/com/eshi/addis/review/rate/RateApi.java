package com.eshi.addis.review.rate;

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

public interface RateApi {
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    RateDto createRate(@RequestBody RateDto rate);

    @GetMapping("/{rateId}")
    @ResponseStatus(HttpStatus.OK)
    RateDto getRate(@PathVariable long rateId);

    @PutMapping("/{rateId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    RateDto updateRate(@PathVariable long rateId, @RequestBody Rate rate);

    @DeleteMapping("/{rateId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteRate(@PathVariable long rateId);

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PagedModel<RateDto>> getRates(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                 @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response
    );
}
