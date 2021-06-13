package com.eshi.addis.security.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PendingVerificationRepository extends PagingAndSortingRepository<PendingVerification, Long> {
    Optional<PendingVerification> findByPhoneNoAndOtpAndExpirationDateIsAfter(String phoneNo, String otp, LocalDateTime expires);
}
