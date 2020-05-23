/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eshi.addis.coupon;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author birhane
 */
@RepositoryRestResource(collectionResourceRel = "coupons",path = "coupons")
public interface CouponRespository extends PagingAndSortingRepository<Coupon, Long>{
    Coupon findByCoupon(String c);
}
