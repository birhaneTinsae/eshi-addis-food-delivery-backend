package com.eshi.addis.menu.size;

import com.eshi.addis.menu.Menu;
import com.eshi.addis.utils.Auditable;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "menu_sizes")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MenuSize extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    @ManyToOne(cascade=CascadeType.ALL)
    private Menu menu;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MenuSize menuSize = (MenuSize) o;
        return id != null && Objects.equals(id, menuSize.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

