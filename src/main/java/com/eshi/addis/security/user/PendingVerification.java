package com.eshi.addis.security.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "pendingVerifications")
@Data
public class PendingVerification {
    @GeneratedValue
    @Id
    private long id;
    private LocalDateTime expirationDate;
    private String phoneNo;
    private String otp;
    @ManyToOne
    private User user;
}
