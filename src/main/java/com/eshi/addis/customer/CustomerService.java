package com.eshi.addis.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Customer getCustomer(String customerId);

    void deleteCustomer(String customerId);

    Customer updateCustomer(String customerId, Customer customer);

    Page<Customer> getCustomers(Pageable pageable);

    CustomerAddress addAddress(String customerId, CustomerAddress customerAddress);

    void deleteAddress(long addressId);

    CustomerAddress updateAddress(long addressId, CustomerAddress address);

    CustomerAddress getCustomerAddress(long addressId);


}