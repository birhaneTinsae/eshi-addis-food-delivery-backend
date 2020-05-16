package com.eshi.addis.menu.size;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuSizeService {
    private MenuSizeRepository menuSizeRepository;

    public MenuSizeService(MenuSizeRepository menuSizeRepository) {
        this.menuSizeRepository = menuSizeRepository;
    }

    public List<MenuSize> create(List<MenuSize> sizes) {
        return menuSizeRepository.saveAll(sizes);
    }
}
