package com.eshi.addis.restaurant.branch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;

public interface BranchService {
    Branch createBranch(String restaurantId, @RequestBody Branch Branch);

    Branch getBranch(long branchId);

    Branch updateBranch(long branchId, @RequestBody Branch Branch);

    void deleteBranch(long branchId);

    Page<Branch> getBranches(Pageable pageable);
}
