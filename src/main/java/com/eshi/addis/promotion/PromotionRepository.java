package com.eshi.addis.promotion;

import com.eshi.addis.promotion.Promotion;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends PagingAndSortingRepository<Promotion,Long> {

}
