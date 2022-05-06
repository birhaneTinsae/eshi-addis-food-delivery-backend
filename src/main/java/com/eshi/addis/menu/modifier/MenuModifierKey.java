package com.eshi.addis.menu.modifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class MenuModifierKey implements Serializable {
    private long menuId;
    private long modifierId;
}
