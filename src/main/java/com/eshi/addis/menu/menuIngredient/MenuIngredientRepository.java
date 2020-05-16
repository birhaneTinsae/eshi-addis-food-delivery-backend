package com.eshi.addis.menu.menuIngredient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuIngredientRepository extends JpaRepository<MenuIngredient, MenuIngredientKey> {
}
