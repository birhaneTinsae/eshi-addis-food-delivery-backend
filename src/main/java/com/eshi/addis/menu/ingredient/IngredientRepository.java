package com.eshi.addis.menu.ingredient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    Page<Ingredient> findAllByRestaurant_Id(String branchId, Pageable pageable);
}
