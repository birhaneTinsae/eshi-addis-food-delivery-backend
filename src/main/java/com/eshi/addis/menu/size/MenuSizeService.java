package com.eshi.addis.menu.size;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class MenuSizeService implements Common<MenuSize,MenuSize> {
    private final MenuSizeRepository menuSizeRepository;

    @Override
    public MenuSize store(MenuSize menuSize) {
        return menuSizeRepository.save(menuSize);
    }

    @Override
    public Iterable<MenuSize> store(List<@Valid MenuSize> t) {
        return menuSizeRepository.saveAll(t);
    }

    @Override
    public MenuSize show(long id) {
        return menuSizeRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(MenuSize.class,"Id",String.valueOf(id)));
    }

    @Override
    public MenuSize update(long id, MenuSize menuSize) {
        MenuSize ms = show(id);
        BeanUtils.copyProperties(menuSize,ms,getNullPropertyNames(menuSize));
        return menuSizeRepository.save(ms);
    }

    @Override
    public boolean delete(long id) {
        menuSizeRepository.deleteById(id);
        return menuSizeRepository.existsById(id);
    }

    @Override
    public Iterable<MenuSize> getAll(Pageable pageable) {
        return menuSizeRepository.findAll(pageable);
    }
}
