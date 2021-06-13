package com.eshi.addis.restaurant.category;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final RestaurantService restaurantService;

    @Override
    public Category createCategory(String restaurantId, Category category) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
//        var c = new Category();
//        category.setTitle(menuCategoryDto.getTitle());
//        category.setRestaurant(restaurant);
//        if (!isNull(menuCategoryDto.getMenus()) && !menuCategoryDto.getMenus().isEmpty()) {
//            category.setMenus(menuCategoryDto.getMenus());
//        }
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException(Category.class, "id", String.valueOf(categoryId)));
    }

    @Override
    public Category updateCategory(long categoryId, Category category) {
        var c = getCategory(categoryId);
        BeanUtils.copyProperties(category, c, getNullPropertyNames(category));
        return categoryRepository.save(c);
    }

    @Override
    public void deleteCategory(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Page<Category> getCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Page<Category> getRestaurantCategories(String restaurantId, Pageable pageable) {
        return categoryRepository.findAllByRestaurant_Id(restaurantId,pageable);
    }


}
