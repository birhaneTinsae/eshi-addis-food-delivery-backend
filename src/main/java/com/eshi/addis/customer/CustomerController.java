package com.eshi.addis.customer;

import com.eshi.addis.utils.Common;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController implements Common<Customer,Customer> {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
