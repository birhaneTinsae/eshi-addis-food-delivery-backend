package com.eshi.addis.repository;

import com.eshi.addis.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
  //  Iterable<Menu> findByServiceProvider(long id);
}
