package com.eshi.addis.restaurant.category;

import com.eshi.addis.dto.CategoryDTO;
import com.eshi.addis.dto.RestaurantDTO;
import com.eshi.addis.favourite.FavouriteDTO;
import com.eshi.addis.utils.PaginatedResultsRetrievedEvent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

import static com.eshi.addis.utils.Util.dtoMapper;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController implements CategoryAPI {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public CategoryDTO createCategory(String restaurantId, CategoryDTO category) {
        return dtoMapper(categoryService.createCategory(restaurantId, dtoMapper(category, Category.class, modelMapper)), CategoryDTO.class, modelMapper);
    }

    @Override
    public CategoryDTO getCategory(long categoryId) {
        return dtoMapper(categoryService.getCategory(categoryId), CategoryDTO.class, modelMapper);
    }

    @Override
    public CategoryDTO updateCategory(long categoryId, CategoryDTO category) {
        return dtoMapper(categoryService.updateCategory(categoryId, dtoMapper(category, Category.class, modelMapper)), CategoryDTO.class, modelMapper);
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @Override
    public ResponseEntity<PagedModel<CategoryDTO>> getRestaurantMenuCategories(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response, String restaurantId) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                CategoryDTO.class, uriBuilder, response, pageable.getPageNumber(), categoryService.getRestaurantCategories(restaurantId, pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<CategoryDTO>>(assembler.toModel(categoryService.getRestaurantCategories(restaurantId, pageable).map(category -> dtoMapper(category, CategoryDTO.class, modelMapper))), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<PagedModel<CategoryDTO>> getCategory(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        eventPublisher.publishEvent(new PaginatedResultsRetrievedEvent<>(
                CategoryDTO.class, uriBuilder, response, pageable.getPageNumber(), categoryService.getCategories(pageable).getTotalPages(), pageable.getPageSize()));
        return new ResponseEntity<PagedModel<CategoryDTO>>(assembler.toModel(categoryService.getCategories(pageable).map(category -> dtoMapper(category, CategoryDTO.class, modelMapper))), HttpStatus.OK);

    }

}
