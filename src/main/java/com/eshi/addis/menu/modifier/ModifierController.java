package com.eshi.addis.menu.modifier;

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
@RequestMapping("modifiers")
@RequiredArgsConstructor
public class ModifierController implements ModifierAPI {
    private final ModifierService modifierService;
    private final ModifierMapper modifierMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ModifierDTO createModifier(String restaurantId, ModifierDTO modifier) {
        return modifierMapper.toModifierDTO(modifierService.createModifier(restaurantId, modifierMapper.toModifier(modifier)));
    }

    @Override
    public ModifierDTO getModifier(long modifierId) {
        return modifierMapper.toModifierDTO(modifierService.getModifier(modifierId));
    }

    @Override
    public void deleteModifier(long modifierId) {
        modifierService.deleteModifier(modifierId);
    }

    @Override
    public ResponseEntity<PagedModel<ModifierDTO>> getModifiersByRestaurant(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String restaurantId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                ModifierDTO.class, uriBuilder, response, pageable.getPageNumber(), modifierService.getModifiersByRestaurant(restaurantId,pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<ModifierDTO>>(assembler.toModel(modifierService.getModifiersByRestaurant(restaurantId,pageable).map(modifierMapper::toModifierDTO)), HttpStatus.OK);

    }
}
