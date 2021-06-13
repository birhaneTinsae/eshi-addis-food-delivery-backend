package com.eshi.addis.menu.modifier;

import com.eshi.addis.favourite.FavouriteDTO;
import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
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
@RequestMapping("modifiers")
@RequiredArgsConstructor
public class ModifierController implements ModifierAPI {
    private final ModifierService modifierService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ModifierDTO createModifier(String restaurantId, ModifierDTO modifier) {
        return dtoMapper(modifierService.createModifier(restaurantId, dtoMapper(modifier, Modifier.class, modelMapper)), ModifierDTO.class, modelMapper);
    }

    @Override
    public ModifierDTO getModifier(long modifierId) {
        return dtoMapper(modifierService.getModifier(modifierId), ModifierDTO.class, modelMapper);
    }

    @Override
    public void deleteModifier(long modifierId) {
        modifierService.deleteModifier(modifierId);
    }

    @Override
    public ResponseEntity<PagedModel<ModifierDTO>> getModifiersByRestaurant(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String restaurantId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ModifierDTO.class, uriBuilder, response, pageable.getPageNumber(), modifierService.getModifiersByRestaurant(restaurantId,pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ModifierDTO>>(assembler.toModel(modifierService.getModifiersByRestaurant(restaurantId,pageable).map(modifier -> dtoMapper(modifier, ModifierDTO.class, modelMapper))), HttpStatus.OK);

    }
}
