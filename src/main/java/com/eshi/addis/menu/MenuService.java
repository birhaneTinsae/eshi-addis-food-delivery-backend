package com.eshi.addis.menu;

import com.eshi.addis.menu.modifier.MenuModifier;
import com.eshi.addis.menu.modifier.MenuModifierDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService {

    Menu createMenu(long categoryId, Menu menu);

    Menu getMenu(long menuId);

    Menu updateMenu(long menuId, Menu menu);

    void deleteMenu(long menuId);

    Page<Menu> getMenuByCategory(long categoryId, Pageable pageable);

    Page<Menu> getMenuByRestaurant(String restaurantId, Pageable pageable);

    Page<Menu> getMenus(Pageable pageable);

    Iterable<MenuModifier> addModifier(long menuId, List<MenuModifierDTO> modifier);

    void deleteModifier(long menuId, long modifier);

}
