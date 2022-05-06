package com.eshi.addis.menu;

import com.eshi.addis.menu.modifier.MenuModifier;
import com.eshi.addis.menu.modifier.MenuModifierDTO;
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
import java.util.List;

@RestController
@RequestMapping("menus")
@RequiredArgsConstructor
public class MenuController implements MenuApi {
    private final MenuService menuService;
    private final MenuMapper menuMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public MenuDto createMenu(long categoryId, MenuDto menu) {
        return menuMapper.toMenuDTO(menuService.createMenu(categoryId, menuMapper.toMenu(menu)));
    }

    @Override
    public MenuDto getMenu(long menuId) {
        return menuMapper.toMenuDTO(menuService.getMenu(menuId));
    }

    @Override
    public MenuDto updateMenu(long menuId, MenuDto menu) {
        return menuMapper.toMenuDTO(menuService.updateMenu(menuId, menuMapper.toMenu(menu)));
    }

    @Override
    public void deleteMenu(long menuId) {
        menuService.deleteMenu(menuId);
    }

    @Override
    public ResponseEntity<PagedModel<MenuDto>> getMenuByCategory(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, long categoryId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MenuDto.class, uriBuilder, response, pageable.getPageNumber(), menuService.getMenuByCategory(categoryId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MenuDto>>(assembler.toModel(menuService.getMenuByCategory(categoryId, pageable).map(menuMapper::toMenuDTO)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<MenuDto>> getMenuByRestaurant(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String restaurantId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MenuDto.class, uriBuilder, response, pageable.getPageNumber(), menuService.getMenuByRestaurant(restaurantId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MenuDto>>(assembler.toModel(menuService.getMenuByRestaurant(restaurantId, pageable).map(menuMapper::toMenuDTO)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PagedModel<MenuDto>> getMenus(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                MenuDto.class, uriBuilder, response, pageable.getPageNumber(), menuService.getMenus(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<MenuDto>>(assembler.toModel(menuService.getMenus(pageable).map(menuMapper::toMenuDTO)), HttpStatus.OK);
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
