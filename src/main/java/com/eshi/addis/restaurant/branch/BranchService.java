package com.eshi.addis.restaurant.branch;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class BranchService implements Common<Branch, Branch> {
    private final BranchRepository branchRepository;

    @Override
    public Branch store(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Iterable<Branch> store(List<Branch> t) {
        return branchRepository.saveAll(t);
    }

    @Override
    public Branch show(long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Branch.class, "id", String.valueOf(id)));
    }

    @Override
    public Branch update(long id, Branch branch) {
        Branch c = show(id);
        BeanUtils.copyProperties(branch, c, getNullPropertyNames(branch));
        return branchRepository.save(c);
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public Iterable<Branch> getAll(Pageable pageable) {
        return branchRepository.findAll(pageable);
    }
}
