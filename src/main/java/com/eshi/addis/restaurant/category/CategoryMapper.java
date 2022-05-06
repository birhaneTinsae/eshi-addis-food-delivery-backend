package com.eshi.addis.restaurant.category;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryDto categoryDTO);

    CategoryDto toCategoryDTO(Category category);
}
