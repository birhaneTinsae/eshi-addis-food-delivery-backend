package com.eshi.addis.restaurant.branch;

import com.eshi.addis.exception.EntityNotFoundException;
import com.eshi.addis.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.eshi.addis.utils.Util.getNullPropertyNames;

@Service
@RequiredArgsConstructor
public class BranchServiceImp implements BranchService {
    private final BranchRepository branchRepository;

    @Override
    public Branch createBranch(String restaurantId, Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public Branch getBranch(long branchId) {
        return branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException(Branch.class, "id", String.valueOf(branchId)));
    }

    @Override
    public Branch updateBranch(long branchId, Branch branch) {
        var b = getBranch(branchId);
        BeanUtils.copyProperties(branch, b, getNullPropertyNames(branch));
        return branchRepository.save(b);
    }

    @Override
    public void deleteBranch(long branchId) {
        branchRepository.deleteById(branchId);
    }

    @Override
    public Page<Branch> getBranches(Pageable pageable) {
        return branchRepository.findAll(pageable);
    }
}
