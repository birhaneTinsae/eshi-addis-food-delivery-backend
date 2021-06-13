package com.eshi.addis.rate;

import com.eshi.addis.dto.OrderDTO;
import com.eshi.addis.dto.RestaurantDTO;
import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

import static com.eshi.addis.utils.Util.dtoMapper;

@RestController()
@RequestMapping("rates")
@RequiredArgsConstructor
public class RateController implements RateAPI {
    private final RateService rateService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public RateDTO createRate(RateDTO rate) {
        return dtoMapper(rateService.createRate(dtoMapper(rate, Rate.class, modelMapper)), RateDTO.class, modelMapper);
    }

    @Override
    public RateDTO getRate(long rateId) {
        return dtoMapper(rateService.getRate(rateId), RateDTO.class, modelMapper);

    }

    @Override
    public RateDTO updateRate(long rateId, Rate rate) {
        return dtoMapper(rateService.updateRate(rateId, dtoMapper(rate, Rate.class, modelMapper)), RateDTO.class, modelMapper);

    }

    @Override
    public void deleteRate(long rateId) {
        rateService.deleteRate(rateId);
    }

    @Override
    public ResponseEntity<PagedModel<RateDTO>> getRates(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                RateDTO.class, uriBuilder, response, pageable.getPageNumber(), rateService.getRates(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<RateDTO>>(assembler.toModel(rateService.getRates(pageable).map(rate -> dtoMapper(rate, RateDTO.class, modelMapper))), HttpStatus.OK);
    }
}
