package com.eshi.addis.security;

import com.enatsystem.hr.security.Role;
import com.enatsystem.hr.utils.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity(name = "privileges")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Privilege extends Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
    @JsonIgnoreProperties(value = {"privileges"})
    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    private List<Role> roles;
}
