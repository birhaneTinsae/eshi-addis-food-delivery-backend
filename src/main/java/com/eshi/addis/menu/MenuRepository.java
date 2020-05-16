package com.eshi.addis.menu;

import com.eshi.addis.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
  //  Iterable<Menu> findByServiceProvider(long id);
}
