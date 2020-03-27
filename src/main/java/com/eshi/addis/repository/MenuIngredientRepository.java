package com.eshi.addis.repository;

import com.eshi.addis.model.MenuIngredient;
import com.eshi.addis.model.MenuIngredientKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuIngredientRepository extends JpaRepository<MenuIngredient, MenuIngredientKey> {
}
