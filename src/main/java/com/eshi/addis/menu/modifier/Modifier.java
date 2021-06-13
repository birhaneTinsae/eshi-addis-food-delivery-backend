package com.eshi.addis.menu.modifier;

import com.eshi.addis.restaurant.Restaurant;
import com.eshi.addis.utils.Auditable;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "modifiers")
public class Modifier extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToOne
    private Restaurant restaurant;
    @OneToMany(mappedBy = "modifier")
    private List<MenuModifier> modifiers;
}
