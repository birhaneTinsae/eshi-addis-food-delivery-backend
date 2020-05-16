package com.eshi.addis.restaurant.category;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.utils.Common;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
public class CategoryService implements Common<Category,Category> {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    public Category getCategory(long categoryId) {
        return categoryRepository.getOne(categoryId);
    }

    @Override
    public Category store(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Iterable<Category> store(List<Category> t) {
        return categoryRepository.saveAll(t);
    }

    @Override
    public Category show(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(Category.class,"id",String.valueOf(id)));
    }

    @Override
    public Category update(long id, Category category) {
        Category c = show(id);
        BeanUtils.copyProperties(category,c,getNullPropertyNames(category));
        return categoryRepository.save(c);
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Iterable<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
