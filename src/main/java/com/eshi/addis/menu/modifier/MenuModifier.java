package com.eshi.addis.menu.modifier;

import com.eshi.addis.menu.Menu;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
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

    public MenuModifier(Menu menu, MenuModifierDTO modifier) {
        this.id = new MenuModifierKey(menu.getId(), modifier.getModifier().getId());
        this.menu = menu;
        this.modifier = modifier.getModifier();
        this.price = modifier.getPrice();
        this.maxQty = modifier.getMaxQty();
        this.minQty = modifier.getMinQty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MenuModifier that = (MenuModifier) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
