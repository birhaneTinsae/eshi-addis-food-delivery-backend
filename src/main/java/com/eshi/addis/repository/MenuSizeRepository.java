package com.eshi.addis.repository;

import com.eshi.addis.model.MenuSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuSizeRepository extends JpaRepository<MenuSize,Long> {
}
