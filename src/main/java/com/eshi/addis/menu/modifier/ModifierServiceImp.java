package com.eshi.addis.menu.modifier;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class ModifierServiceImp implements ModifierService {
    private final ModifierRepository modifierRepository;
    private final RestaurantService restaurantService;

    @Override
    public Modifier createModifier(String restaurantId, Modifier modifier) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        modifier.setRestaurant(restaurant);
        return modifierRepository.save(modifier);
    }

    @Override
    public Modifier getModifier(long modifierId) {
        return modifierRepository.findById(modifierId)
                .orElseThrow(() -> new EntityNotFoundException(Modifier.class, "Id", String.valueOf(modifierId)));
    }

    @Override
    public Modifier updateModifier(long modifierId, Modifier modifier) {
        var m = getModifier(modifierId);
        BeanUtils.copyProperties(modifier,m,getNullPropertyNames(modifier));
        return modifierRepository.save(m);
    }

    @Override
    public void deleteModifier(long modifierId) {
        modifierRepository.deleteById(modifierId);
    }

    @Override
    public Page<Modifier> getModifiersByRestaurant(String restaurantId, Pageable pageable) {
        return modifierRepository.findAllByRestaurant_Id(restaurantId,pageable);
    }
}
