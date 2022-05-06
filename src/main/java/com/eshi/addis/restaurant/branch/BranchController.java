package com.eshi.addis.restaurant.branch;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("branches")
@RequiredArgsConstructor
public class BranchController implements BranchApi {
    private final BranchServiceImp branchServiceImp;

    @Override
    public BranchDto createBranch(String restaurantId, BranchDto Branch) {
        return null;
    }

    @Override
    public BranchDto getBranch(long branchId) {
        return null;
    }

    @Override
    public BranchDto updateBranch(long branchId, BranchDto Branch) {
        return null;
    }

    @Override
    public void deleteBranch(long branchId) {

    }

    @Override
    public ResponseEntity<PagedModel<BranchDto>> getBranches(Pageable pageable, PagedResourcesAssembler assembler, UriComponentsBuilder uriBuilder, HttpServletResponse response) {
        return null;
    }
}
