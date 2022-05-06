package com.eshi.addis.promotion;

import com.eshi.addis.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotions")
@RequiredArgsConstructor
public class PromotionController implements Common<Promotion,Promotion> {
    private final PromotionServiceImp promotionServiceImp;
    @Override
    public Promotion store(Promotion promotion) {
        return null;
    }

    @Override
    public Iterable<Promotion> store(List<Promotion> t) {
        return null;
    }

    @Override
    public Promotion show(long id) {
        return null;
    }

    @Override
    public Promotion update(long id, Promotion promotion) {
        return null;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Iterable<Promotion> getAll(Pageable pageable) {
        return null;
    }
}
