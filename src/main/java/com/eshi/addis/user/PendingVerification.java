package com.eshi.addis.user;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "pendingVerifications")
public class PendingVerification {
    @GeneratedValue
    @Id
    private long id;
    private LocalDateTime expirationDate;
    @ManyToOne
    private User user;
}
