package com.eshi.addis.menu.modifier;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuModifierRepository extends PagingAndSortingRepository<MenuModifier,MenuModifierKey> {
}
