package com.eshi.addis.favourite;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavouriteRepository extends PagingAndSortingRepository<Favourite, Long> {
    Page<Favourite> findAllByCustomer_Id(String customerId, Pageable pageable);

}
