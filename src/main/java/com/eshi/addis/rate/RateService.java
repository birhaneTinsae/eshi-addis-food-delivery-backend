package com.eshi.addis.rate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

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
