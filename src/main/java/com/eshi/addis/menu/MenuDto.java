package com.eshi.addis.menu;

import com.eshi.addis.menu.menuIngredient.MenuIngredient;
import com.eshi.addis.menu.modifier.MenuModifier;
import com.eshi.addis.menu.size.MenuSize;
import com.eshi.addis.order.Status;
import com.eshi.addis.restaurant.category.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class MenuDto implements Serializable {
    //    private Menu menu;
//    private Set<IngredientDTO> ingredients;
    private long id;
    private double price;
    private List<MenuSize> sizes;
    private String thumbnailPic;
    @NotNull(message = "Menu name is required")
    @NotBlank(message = "Menu name shouldn't be blank")
    private String name;
    private Category category;
    private List<MenuIngredient> ingredients;
    private boolean available = true;
    private Status status;
    private List<MenuModifier> menuModifiers;

}
