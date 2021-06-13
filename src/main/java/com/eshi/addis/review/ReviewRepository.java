package com.eshi.addis.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends PagingAndSortingRepository<Review,Long> {
    Page<Review> findAllByRestaurant_Id(String restaurantId, Pageable pageable);
    Page<Review> findAllByCustomer_Id(String customerId,Pageable pageable);
}
