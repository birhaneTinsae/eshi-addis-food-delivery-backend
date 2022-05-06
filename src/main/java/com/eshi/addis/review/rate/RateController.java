package com.eshi.addis.review.rate;

import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("rates")
@RequiredArgsConstructor
public class RateController implements RateApi {
    private final RateService rateService;
    private final RateMapper rateMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public RateDto createRate(RateDto rate) {
        return rateMapper.toRateDTO(rateService.createRate(rateMapper.toRate(rate)));
    }

    @Override
    public RateDto getRate(long rateId) {
        return rateMapper.toRateDTO(rateService.getRate(rateId));

    }

    @Override
    public RateDto updateRate(long rateId, Rate rate) {
        return rateMapper.toRateDTO(rateService.updateRate(rateId, rate));

    }

    @Override
    public void deleteRate(long rateId) {
        rateService.deleteRate(rateId);
    }

    @Override
    public ResponseEntity<PagedModel<RateDto>> getRates(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RateDto.class, uriBuilder, response, pageable.getPageNumber(), rateService.getRates(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RateDto>>(assembler.toModel(rateService.getRates(pageable).map(rateMapper::toRateDTO)), HttpStatus.OK);
    }
}
