package com.eshi.addis.menu;

import com.eshi.addis.dto.MenuDTO;
import com.eshi.addis.menu.modifier.MenuModifier;
import com.eshi.addis.menu.modifier.MenuModifierDTO;
import com.eshi.addis.menu.size.MenuSizeService;
import com.eshi.addis.restaurant.category.CategoryService;
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

import java.util.List;

import static com.eshi.addis.utils.Util.dtoMapper;

@RestController
@RequestMapping("menus")
@RequiredArgsConstructor
public class MenuController implements MenuAPI {
    private final MenuService menuService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public MenuDTO createMenu(long categoryId, MenuDTO menu) {
        return dtoMapper(menuService.createMenu(categoryId, dtoMapper(menu, Menu.class, modelMapper)), MenuDTO.class, modelMapper);
    }

    @Override
    public MenuDTO getMenu(long menuId) {
        return dtoMapper(menuService.getMenu(menuId), MenuDTO.class, modelMapper);
    }

    @Override
    public MenuDTO updateMenu(long menuId, MenuDTO menu) {
        return dtoMapper(menuService.updateMenu(menuId, dtoMapper(menu, Menu.class, modelMapper)), MenuDTO.class, modelMapper);
    }

    @Override
    public void deleteMenu(long menuId) {
        menuService.deleteMenu(menuId);
    }

    @Override
    public ResponseEntity<PagedModel<MenuDTO>> getMenuByCategory(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, long categoryId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MenuDTO.class, uriBuilder, response, pageable.getPageNumber(), menuService.getMenuByCategory(categoryId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MenuDTO>>(assembler.toModel(menuService.getMenuByCategory(categoryId, pageable).map(category -> dtoMapper(category, MenuDTO.class, modelMapper))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<MenuDTO>> getMenuByRestaurant(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String restaurantId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MenuDTO.class, uriBuilder, response, pageable.getPageNumber(), menuService.getMenuByRestaurant(restaurantId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MenuDTO>>(assembler.toModel(menuService.getMenuByRestaurant(restaurantId, pageable).map(category -> dtoMapper(category, MenuDTO.class, modelMapper))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<MenuDTO>> getMenus(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MenuDTO.class, uriBuilder, response, pageable.getPageNumber(), menuService.getMenus(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MenuDTO>>(assembler.toModel(menuService.getMenus(pageable).map(category -> dtoMapper(category, MenuDTO.class, modelMapper))), HttpStatus.OK);
    }

    @Override
    public Iterable<MenuModifier> addModifier(long menuId, List<MenuModifierDTO> modifier) {
        return menuService.addModifier(menuId, modifier);
    }

    @Override
    public void deleteModifier(long menuId, long modifierId) {
        menuService.deleteModifier(menuId, modifierId);
    }


}
