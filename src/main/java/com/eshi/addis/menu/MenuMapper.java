package com.eshi.addis.menu;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuDto toMenuDTO(Menu menu);

    Menu toMenu(MenuDto menuDTO);
}
