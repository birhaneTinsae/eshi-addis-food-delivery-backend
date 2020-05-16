package com.eshi.addis.menu.size;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuSizeRepository extends JpaRepository<MenuSize,Long> {
}
