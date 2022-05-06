package com.eshi.addis.customer;

import com.eshi.addis.dto.CustomerDTO;
import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController implements CustomerAPI {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public CustomerDTO createCustomer(Customer customer) {
        return customerMapper.toCustomerDTO(customerService.createCustomer(customer));
    }

    @Override
    public CustomerDTO getCustomer(String customerId) {
        return customerMapper.toCustomerDTO(customerService.getCustomer(customerId));
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerService.deleteCustomer(customerId);
    }

    @Override
    public CustomerDTO updateCustomer(String customerId, Customer customer) {
        return customerMapper.toCustomerDTO(customerService.updateCustomer(customerId, customer));
    }

    @Override
    public ResponseEntity<PagedModel<CustomerDTO>> getCustomers(@Parameter(description = "pagination object",
            schema = @Schema(implementation = Pageable.class))
                                                                @Valid Pageable pageable
            , PagedResourcesAssembler assembler
            , UriComponentsBuilder uriBuilder

            , final HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                CustomerDTO.class, uriBuilder, response, pageable.getPageNumber(), customerService.getCustomers(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<CustomerDTO>>(assembler.toModel(customerService.getCustomers(pageable).map(customerMapper::toCustomerDTO)), HttpStatus.OK);

    }

    @Override
    public CustomerAddressDTO addAddress(String customerId, CustomerAddress customerAddress) {
        return customerMapper.toCustomerAddressDTO(customerService.addAddress(customerId, customerAddress));
    }

    @Override
    public void deleteAddress(long addressId) {
        customerService.deleteAddress(addressId);
    }

    @Override
    public CustomerAddressDTO updateAddress(long addressId, CustomerAddress address) {
        return customerMapper.toCustomerAddressDTO(customerService.updateAddress(addressId, address));

    }

    @Override
    public CustomerAddressDTO getCustomerAddress(long addressId) {
        return customerMapper.toCustomerAddressDTO(customerService.getCustomerAddress(addressId));
    }
}
