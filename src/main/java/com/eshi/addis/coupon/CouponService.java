package com.eshi.addis.coupon;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CouponService {

    Iterable<Coupon> createCoupons(CouponDTO couponDTO);

    void deleteCoupon(long couponId);

    Coupon updateCoupon(long couponId, Coupon coupon);

    Page<Coupon> getCoupons(Pageable pageable);

    Coupon getCoupon(long couponId);

    Coupon getCouponByCode(String code);

    Page<Coupon> getCouponsByRestaurant(String restaurantId, Pageable pageable);

    Coupon getCouponByRestaurant(long id, String restaurantId);


}
