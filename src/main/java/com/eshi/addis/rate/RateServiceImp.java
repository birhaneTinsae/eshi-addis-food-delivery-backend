package com.eshi.addis.rate;

import com.eshi.addis.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class RateServiceImp implements RateService {
    private final RateRepository rateRepository;

    @Override
    public Rate createRate(Rate rate) {
        return rateRepository.save(rate);
    }

    @Override
    public Rate getRate(long rateId) {
        return rateRepository.findById(rateId)
                .orElseThrow(() -> new EntityNotFoundException(Rate.class, "Id", String.valueOf(rateId)));
    }

    @Override
    public Rate updateRate(long rateId, Rate rate) {
        var r = getRate(rateId);
        BeanUtils.copyProperties(rate, r, getNullPropertyNames(rate));
        return rateRepository.save(r);
    }

    @Override
    public void deleteRate(long rateId) {
        rateRepository.deleteById(rateId);
    }

    @Override
    public Page<Rate> getRates(Pageable pageable) {
        return rateRepository.findAll(pageable);
    }

    @Override
    public List<Rate> getRates() {
        return (List<Rate>) rateRepository.findAll();
    }

    @Override
    public Rate getRateByKM(double distance) {
        return getRates().stream()
                .filter(rate ->
                        distance >= rate.getFrom() && distance <= rate.getTo())
                .reduce((a, b) -> {
                    throw new IllegalStateException("Multiple elements: " + a + ", " + b);
                }).orElseThrow(() -> new IllegalStateException("No tax rate"));
    }

}
