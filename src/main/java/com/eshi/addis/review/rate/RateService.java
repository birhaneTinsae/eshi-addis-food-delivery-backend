package com.eshi.addis.review.rate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RateService {

    Rate createRate(Rate rate);

    Rate getRate(long rateId);

    Rate updateRate(long rateId, Rate rate);

    void deleteRate(long rateId);

    Page<Rate> getRates(Pageable pageable);

    List<Rate> getRates();

    Rate getRateByKM(double distance);
}
