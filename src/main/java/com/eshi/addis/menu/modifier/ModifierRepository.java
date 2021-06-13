package com.eshi.addis.menu.modifier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifierRepository extends PagingAndSortingRepository<Modifier, Long> {
    Page<Modifier> findAllByRestaurant_Id(String restaurantId, Pageable pageable);
}
