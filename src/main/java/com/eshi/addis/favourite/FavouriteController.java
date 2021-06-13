package com.eshi.addis.favourite;

import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static com.eshi.addis.utils.Util.dtoMapper;

@RequiredArgsConstructor
public class FavouriteController implements FavouriteAPI {
    private final FavouriteService favouriteService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public FavouriteDTO createFavourite(Favourite favourite) {
        return dtoMapper(favouriteService.createFavourite(favourite), FavouriteDTO.class, modelMapper);
    }

    @Override
    public void removeFavourite(long id) {
        favouriteService.removeFavourite(id);
    }

    @Override
    public ResponseEntity<PagedModel<FavouriteDTO>> getCustomerFavourites(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String customerId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                FavouriteDTO.class, uriBuilder, response, pageable.getPageNumber(), favouriteService.getCustomerFavourites(customerId,pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<FavouriteDTO>>(assembler.toModel(favouriteService.getCustomerFavourites(customerId,pageable).map(favourite -> dtoMapper(favourite, FavouriteDTO.class, modelMapper))), HttpStatus.OK);

    }


}
