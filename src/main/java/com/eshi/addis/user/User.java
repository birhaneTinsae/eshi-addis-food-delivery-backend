package com.eshi.addis.user;


import com.eshi.addis.manager.Manager;
import com.eshi.addis.security.Role;
import com.eshi.addis.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "users")
@Data
@EntityListeners(AuditingEntityListener.class)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "username")
public class User extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    private String fullName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"users"})
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
    @Email
    private String email;
    private boolean enabled;
    private boolean active;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean firstLogin;
    @OneToOne
    @JoinColumn(referencedColumnName = "employeeId", name = "employee_id")
    private Manager manager;
//    @ManyToOne
//    @JoinColumn(referencedColumnName = "code",name = "branch_code")
//    @JsonIgnoreProperties(value = {"users"})
//    private Branch branch;

//    @ManyToOne
//    @JoinColumn(referencedColumnName = "code",name = "department_code")
//    @JsonIgnoreProperties(value = {"users"})
//    private Department department;
}
