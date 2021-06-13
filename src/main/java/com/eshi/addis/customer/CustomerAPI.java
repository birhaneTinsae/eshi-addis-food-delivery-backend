package com.eshi.addis.customer;

import com.eshi.addis.dto.CustomerDTO;
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

public interface CustomerAPI {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    CustomerDTO createCustomer(@RequestBody() Customer customer);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{customerId}")
    CustomerDTO getCustomer(@PathVariable("customerId") String customerId);

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteCustomer(@PathVariable("customerId") String customerId);

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    CustomerDTO updateCustomer(@PathVariable("customerId") String customerId, @RequestBody() Customer customer);

    @GetMapping()
    ResponseEntity<PagedModel<CustomerDTO>> getCustomers(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                         @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/address/{customerId}")
    CustomerAddressDTO addAddress(@PathVariable("customerId") String customerId, @RequestBody() CustomerAddress customerAddress);

    @DeleteMapping("/{addressId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteAddress(@PathVariable("addressId") long addressId);

    @PutMapping("/{addressId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    CustomerAddressDTO updateAddress(@PathVariable("addressId") long addressId, @RequestBody() CustomerAddress address);

    @GetMapping("/address/{addressId}")
    CustomerAddressDTO getCustomerAddress(@PathVariable("addressId") long addressId);


}