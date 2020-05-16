package com.eshi.addis.menu;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.menu.menuIngredient.MenuIngredient;
import com.eshi.addis.menu.menuIngredient.MenuIngredientRepository;
import com.eshi.addis.order.Status;
import com.eshi.addis.utils.Common;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
public class MenuService implements Common<Menu, Menu> {
    private MenuRepository menuRepository;
    private MenuIngredientRepository menuIngredientRepository;

    public MenuService(MenuRepository menuRepository, MenuIngredientRepository menuIngredientRepository) {
        this.menuRepository = menuRepository;
        this.menuIngredientRepository = menuIngredientRepository;
    }


    public List<MenuIngredient> createMenuIngredient(List<MenuIngredient> menuIngredientList) {
        return menuIngredientRepository.saveAll(menuIngredientList);
    }

    @Override
    public Menu store(@Valid Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Iterable<Menu> store(List<@Valid Menu> t) {
        return menuRepository.saveAll(t);
    }

    @Override
    public Menu show(long id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Menu.class, "id", String.valueOf(id)));
    }

    @Override
    public Menu update(long id, @Valid Menu menu) {
        Menu m = show(id);
        BeanUtils.copyProperties(menu, m, getNullPropertyNames(menu));
        return menuRepository.save(m);
    }

    public boolean delete(long id) {
        Menu m = show(id);
        m.setStatus(Status.DELETED);
        update(id, m);
        return true;
    }

    @Override
    public Iterable<Menu> getAll(Pageable pageable) {
        return menuRepository.findAll(pageable);
    }
}
