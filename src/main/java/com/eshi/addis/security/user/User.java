package com.eshi.addis.security.user;


import com.eshi.addis.security.role.Role;
import com.eshi.addis.utils.Auditable;
import com.eshi.addis.validation.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
@Data
@EntityListeners(AuditingEntityListener.class)
public class User extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull(message = "Username is required.")
    private String username;
    private String fullName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @ValidPassword(message = "Invalid password format.")
    @NotNull(message = "Password is mandatory")
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
    @Email
    private String email;
    private boolean enabled;
    private boolean active;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean firstLogin;

}
