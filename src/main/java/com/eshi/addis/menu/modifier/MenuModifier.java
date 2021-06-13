package com.eshi.addis.menu.modifier;

import com.eshi.addis.menu.Menu;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Data
@Entity(name = "menu_modifiers")
public class MenuModifier {
    @EmbeddedId
    private MenuModifierKey id;

    @MapsId("menuId")
    @ManyToOne()
    private Menu menu;

    @MapsId("modifierId")
    @ManyToOne()
    private Modifier modifier;

    private double price;
    private int minQty;
    private int maxQty;

}
