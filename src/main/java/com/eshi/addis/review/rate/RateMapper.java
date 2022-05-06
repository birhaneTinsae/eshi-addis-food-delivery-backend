package com.eshi.addis.review.rate;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateMapper {
    Rate toRate(RateDto rateDTO);

    RateDto toRateDTO(Rate rate);
}
