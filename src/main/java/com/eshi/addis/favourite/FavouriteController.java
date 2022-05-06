package com.eshi.addis.favourite;

import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
public class FavouriteController implements FavouriteAPI {
    private final FavouriteService favouriteService;
    private final FavouriteMapper favouriteMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public FavouriteDto createFavourite(Favourite favourite) {
        return favouriteMapper.toFavouriteDTO(favouriteService.createFavourite(favourite));
    }

    @Override
    public void removeFavourite(long id) {
        favouriteService.removeFavourite(id);
    }

    @Override
    public ResponseEntity<PagedModel<FavouriteDto>> getCustomerFavourites(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String customerId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                FavouriteDto.class, uriBuilder, response, pageable.getPageNumber(), favouriteService.getCustomerFavourites(customerId,pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<FavouriteDto>>(assembler.toModel(favouriteService.getCustomerFavourites(customerId,pageable).map(favouriteMapper::toFavouriteDTO)), HttpStatus.OK);

    }


}
