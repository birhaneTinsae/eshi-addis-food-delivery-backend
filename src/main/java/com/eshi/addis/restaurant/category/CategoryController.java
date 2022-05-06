package com.eshi.addis.restaurant.category;

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

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController implements CategoryAPI {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public CategoryDto createCategory(String restaurantId, CategoryDto category) {
        return categoryMapper.toCategoryDTO(categoryService.createCategory(restaurantId, categoryMapper.toCategory(category)));
    }

    @Override
    public CategoryDto getCategory(long categoryId) {
        return categoryMapper.toCategoryDTO(categoryService.getCategory(categoryId));
    }

    @Override
    public CategoryDto updateCategory(long categoryId, CategoryDto category) {
        return categoryMapper.toCategoryDTO(categoryService.updateCategory(categoryId, categoryMapper.toCategory(category)));
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @Override
    public ResponseEntity<PagedModel<CategoryDto>> getRestaurantMenuCategories(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String restaurantId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                CategoryDto.class, uriBuilder, response, pageable.getPageNumber(), categoryService.getRestaurantCategories(restaurantId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<CategoryDto>>(assembler.toModel(categoryService.getRestaurantCategories(restaurantId, pageable).map(categoryMapper::toCategoryDTO)), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<CategoryDto>> getCategory(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                CategoryDto.class, uriBuilder, response, pageable.getPageNumber(), categoryService.getCategories(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<CategoryDto>>(assembler.toModel(categoryService.getCategories(pageable).map(categoryMapper::toCategoryDTO)), HttpStatus.OK);

    }

}
