package com.eshi.addis.menu;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    Page<Menu> findAllByCategory_Restaurant_Id(String id, Pageable pageable);
    Page<Menu> findAllByCategory_Id(long id, Pageable pageable);
}
