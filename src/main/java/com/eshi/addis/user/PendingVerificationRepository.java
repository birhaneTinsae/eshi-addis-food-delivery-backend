package com.eshi.addis.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendingVerificationRepository extends PagingAndSortingRepository<PendingVerification,Long> {
}
