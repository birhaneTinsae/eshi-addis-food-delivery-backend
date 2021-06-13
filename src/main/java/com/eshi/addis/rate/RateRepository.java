package com.eshi.addis.rate;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends PagingAndSortingRepository<Rate, Long> {
}
