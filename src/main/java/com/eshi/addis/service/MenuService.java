package com.eshi.addis.service;

import com.eshi.addis.model.Menu;
import com.eshi.addis.model.MenuIngredient;
import com.eshi.addis.repository.MenuIngredientRepository;
import com.eshi.addis.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private MenuRepository menuRepository;
    private MenuIngredientRepository menuIngredientRepository;

    public MenuService(MenuRepository menuRepository, MenuIngredientRepository menuIngredientRepository) {
        this.menuRepository = menuRepository;
        this.menuIngredientRepository = menuIngredientRepository;
    }

    public Iterable<Menu> getMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenu(long id) {
        return menuRepository.getOne(id);
    }

    public Menu create(Menu menu) {
        return menuRepository.save(menu);
    }

    public Iterable<Menu> getServiceProviderMenus(long id) {
     return null;//  return menuRepository.findByServiceProvider(id);
    }

    public List<MenuIngredient> createMenuIngredient(List<MenuIngredient> menuIngredientList){
        return menuIngredientRepository.saveAll(menuIngredientList);
    }
}
