package com.eshi.addis.promotion;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.promotion.Promotion;
import com.eshi.addis.promotion.PromotionRepository;
import com.eshi.addis.utils.Common;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
public class PromotionService implements Common<Promotion, Promotion> {
    private PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public Promotion store(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Iterable<Promotion> store(List<Promotion> t) {
        return promotionRepository.saveAll(t);
    }

    @Override
    public Promotion show(long id) {
        return promotionRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(Promotion.class,"id",String.valueOf(id)));
    }

    @Override
    public Promotion update(long id, Promotion promotion) {
        Promotion p = show(id);
        BeanUtils.copyProperties(promotion,p,getNullPropertyNames(promotion));

        return promotionRepository.save(p);
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Iterable<Promotion> getAll(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }
}
