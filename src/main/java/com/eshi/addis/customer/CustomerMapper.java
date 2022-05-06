package com.eshi.addis.customer;

import com.eshi.addis.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDTO customerDTO);

    CustomerDTO toCustomerDTO(Customer customer);

    CustomerAddress toCustomerAddress(CustomerAddressDTO customerAddressDTO);

    CustomerAddressDTO toCustomerAddressDTO(CustomerAddress customerAddress);
}
