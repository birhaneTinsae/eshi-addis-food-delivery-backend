package com.eshi.addis.menu.modifier;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class MenuModifierKey implements Serializable {
    private long menuId;
    private long modifierId;
}
