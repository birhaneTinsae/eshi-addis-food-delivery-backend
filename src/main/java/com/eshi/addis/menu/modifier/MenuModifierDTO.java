package com.eshi.addis.menu.modifier;

import lombok.Data;

@Data
public class MenuModifierDTO {
    private int maxQty;
    private int minQty;
    private double price;
    private Modifier modifier;


}
