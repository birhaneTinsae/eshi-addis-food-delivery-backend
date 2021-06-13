package com.eshi.addis.promotion;

import com.eshi.addis.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class PromotionServiceImp implements PromotionService {
    private final PromotionRepository promotionRepository;

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion getPromotion(long promotionId) {
        return promotionRepository.findById(promotionId)
                .orElseThrow(() -> new EntityNotFoundException(Promotion.class, "id", String.valueOf(promotionId)));
    }

    @Override
    public Promotion updatePromotion(long promotionId, Promotion promotion) {
        var p = getPromotion(promotionId);
        BeanUtils.copyProperties(promotion, p, getNullPropertyNames(promotion));

        return promotionRepository.save(p);
    }

    @Override
    public void deletePromotion(long promotionId) {
        promotionRepository.deleteById(promotionId);
    }

    @Override
    public Page<Promotion> getPromotions(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }

}
