package com.eshi.addis.menu.modifier;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModifierMapper {
    Modifier toModifier(ModifierDTO modifierDTO);

    ModifierDTO toModifierDTO(Modifier modifier);
}
