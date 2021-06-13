/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eshi.addis.coupon;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * @author birhane
 */
@RepositoryRestResource(collectionResourceRel = "coupons", path = "coupons")
public interface CouponRepository extends PagingAndSortingRepository<Coupon, Long> {
    Optional<Coupon> findByCode(String c);

    Page<Coupon> findAllByRestaurant_Id(String restaurantId, Pageable pageable);
    Optional<Coupon> findAllByIdAndRestaurant_Id(long id,String restaurantId);
}
