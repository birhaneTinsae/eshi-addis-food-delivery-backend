package com.eshi.addis.customer;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends PagingAndSortingRepository<CustomerAddress,Long> {
}
