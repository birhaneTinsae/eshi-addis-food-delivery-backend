package com.eshi.addis.menu.modifier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ModifierService {
    Modifier createModifier(String restaurantId, Modifier modifier);

    Modifier getModifier(long modifierId);

    Modifier updateModifier(long modifierId, Modifier modifier);

    void deleteModifier(long modifierId);

    Page<Modifier> getModifiersByRestaurant(String restaurantId, Pageable pageable);
}
