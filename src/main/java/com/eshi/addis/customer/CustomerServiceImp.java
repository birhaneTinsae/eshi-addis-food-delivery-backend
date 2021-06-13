package com.eshi.addis.customer;

import com.eshi.addis.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerAddressRepository customerAddressRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(String customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(Customer.class, "id"));
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomer(String customerId, Customer customer) {
        var c = getCustomer(customerId);
        BeanUtils.copyProperties(customer, c, getNullPropertyNames(customer));
        return customerRepository.save(c);
    }

    @Override
    public Page<Customer> getCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public CustomerAddress addAddress(String customerId, CustomerAddress customerAddress) {
        var customer = getCustomer(customerId);
        customerAddress.setCustomer(customer);
        return customerAddressRepository.save(customerAddress);
    }

    @Override
    public void deleteAddress(long addressId) {
        customerAddressRepository.deleteById(addressId);
    }

    @Override
    public CustomerAddress updateAddress(long addressId, CustomerAddress address) {
        var customerAddress = getCustomerAddress(addressId);
        BeanUtils.copyProperties(address,customerAddress,getNullPropertyNames(customerAddress));
        return customerAddressRepository.save(customerAddress);
    }

    @Override
    public CustomerAddress getCustomerAddress(long addressId) {
        return customerAddressRepository.findById(addressId)
                .orElseThrow(()->new EntityNotFoundException(CustomerAddress.class,"Id",String.valueOf(addressId)));
    }

}
