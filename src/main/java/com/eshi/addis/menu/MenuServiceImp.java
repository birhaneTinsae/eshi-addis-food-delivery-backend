package com.eshi.addis.menu;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.menu.modifier.MenuModifier;
import com.eshi.addis.menu.modifier.MenuModifierDTO;
import com.eshi.addis.menu.modifier.MenuModifierKey;
import com.eshi.addis.menu.modifier.MenuModifierRepository;
import com.eshi.addis.menu.size.MenuSizeService;
import com.eshi.addis.restaurant.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class MenuServiceImp implements MenuService {
    private final MenuRepository menuRepository;
    private final MenuSizeService menuSizeService;
    private final CategoryService categoryService;
    private final MenuModifierRepository menuModifierRepository;

    @Override
    public Menu createMenu(long categoryId, Menu menu) {
        var category = categoryService.getCategory(categoryId);
        menu.setCategory(category);
        var m = menuRepository.save(menu);
        m.getSizes().forEach(size -> size.setMenu(m));
        menuSizeService.store(m.getSizes());
        return m;
    }

    @Override
    public Menu getMenu(long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException(Menu.class, "id", String.valueOf(menuId)));
    }

    @Override
    public Menu updateMenu(long menuId, Menu menu) {
        var m = getMenu(menuId);
        BeanUtils.copyProperties(menu, m, getNullPropertyNames(menu));
        return menuRepository.save(m);
    }

    @Override
    public void deleteMenu(long menuId) {
        menuRepository.deleteById(menuId);
    }

    @Override
    public Page<Menu> getMenuByCategory(long categoryId, Pageable pageable) {
        return menuRepository.findAllByCategory_Id(categoryId, pageable);
    }

    @Override
    public Page<Menu> getMenuByRestaurant(String restaurantId, Pageable pageable) {
        return menuRepository.findAllByCategory_Restaurant_Id(restaurantId, pageable);
    }

    @Override
    public Page<Menu> getMenus(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }

    @Override
    public Iterable<MenuModifier> addModifier(long menuId, List<MenuModifierDTO> modifiers) {
        var menu = getMenu(menuId);
        List<MenuModifier> menuModifiers = modifiers.stream().map(modifier -> {
            var menuModifier = new MenuModifier();
            menuModifier.setMaxQty(modifier.getMaxQty());
            menuModifier.setMinQty(modifier.getMinQty());
            menuModifier.setMenu(menu);
            menuModifier.setPrice(modifier.getPrice());
            menuModifier.setModifier(modifier.getModifier());
            return menuModifier;
        }).collect(Collectors.toList());
        return menuModifierRepository.saveAll(menuModifiers);
    }

    @Override
    public void deleteModifier(long menuId, long modifier) {
        var menuModifierKey = new MenuModifierKey();
        menuModifierKey.setMenuId(menuId);
        menuModifierKey.setModifierId(modifier);
        menuModifierRepository.deleteById(menuModifierKey);
    }


}
