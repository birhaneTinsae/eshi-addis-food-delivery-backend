package com.eshi.addis.promotion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionService {

    Promotion createPromotion(Promotion promotion);

    Promotion getPromotion(long promotionId);

    Promotion updatePromotion(long promotionId, Promotion promotion);

    void deletePromotion(long promotionId);

    Page<Promotion> getPromotions(Pageable pageable);


}
