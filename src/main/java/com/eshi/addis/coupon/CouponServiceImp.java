package com.eshi.addis.coupon;

import com.eshi.addis.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.eshi.addis.utils.Util.generateCouponCode;
import static com.eshi.addis.utils.Util.getNullPropertyNames;

@RequiredArgsConstructor
@Service
public class CouponServiceImp implements CouponService {
    private final CouponRepository couponRepository;

    @Override
    public Iterable<Coupon> createCoupons(CouponDTO couponDTO) {
        return couponRepository.saveAll(getCoupons(couponDTO));
    }

    @Override
    public void deleteCoupon(long couponId) {
        couponRepository.deleteById(couponId);
    }

    @Override
    public Coupon updateCoupon(long couponId, Coupon coupon) {
        var c = getCoupon(couponId);
        BeanUtils.copyProperties(coupon, c, getNullPropertyNames(coupon));

//        c.setPercentage(coupon.isPercentage());
//        c.setAmount(coupon.getAmount());
//        c.setGlobal(coupon.isGlobal());
//        c.setExpiryDate(coupon.getExpiryDate());
//        c.setUsed(coupon.isUsed());
        return couponRepository.save(c);
    }

    @Override
    public Page<Coupon> getCoupons(Pageable pageable) {
        return couponRepository.findAll(pageable);
    }

    @Override
    public Coupon getCoupon(long id) {
        return couponRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Coupon.class, "Id", String.valueOf(id)));
    }

    @Override
    public Coupon getCouponByCode(String code) {
        return couponRepository.findByCode(code).orElseThrow(() -> new EntityNotFoundException(Coupon.class, "Code", String.valueOf(code)));
    }

    @Override
    public Page<Coupon> getCouponsByRestaurant(String restaurantId, Pageable pageable) {
        return couponRepository.findAllByRestaurant_Id(restaurantId, pageable);
    }

    @Override
    public Coupon getCouponByRestaurant(long id, String restaurantId) {
        return couponRepository.findAllByIdAndRestaurant_Id(id, restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(Coupon.class, "Id", "Coupon Id " + id + " restaurant Id " + restaurantId));
    }


    private List<Coupon> getCoupons(CouponDTO couponDTO) {
        return IntStream.rangeClosed(0, couponDTO.getQty())
                .boxed()
                .map(x -> {
                    var coupon = new Coupon();
                    coupon.setAmount(couponDTO.getAmount());
                    coupon.setPercentage(couponDTO.isPercentage());
                    coupon.setRestaurant(couponDTO.getRestaurant());
                    coupon.setCode(generateCouponCode());
                    coupon.setExpiryDate(couponDTO.getExpiryDate());
                    coupon.setUsed(couponDTO.isUsed());
                    return coupon;
                }).collect(Collectors.toList());
    }
}
