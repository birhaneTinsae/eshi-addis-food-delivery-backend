package com.eshi.addis.customer;

import com.eshi.addis.utils.Common;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
@Service
public class CustomerService implements Common<Customer, Customer> {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer store(@Valid Customer customer) {
        return null;
    }

    @Override
    public Iterable<Customer> store(List<@Valid Customer> t) {
        return null;
    }

    @Override
    public Customer show(long id) {
        return null;
    }

    @Override
    public Customer update(long id, @Valid Customer customer) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Iterable<Customer> getAll(Pageable pageable) {
        return null;
    }
}
