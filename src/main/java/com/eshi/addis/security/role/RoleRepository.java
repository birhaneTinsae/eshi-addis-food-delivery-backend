package com.eshi.addis.security.role;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
//@RepositoryRestResource(collectionResourceRel = "roles",path = "roles",excerptProjection = RoleProjection.class)
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
    Page<Role> findAllByDeletedAtIsNull(Pageable pageable);

    Optional<Role> findByName(String name);

    boolean existsByName(String roleName);
}

// @Projection(types = {Role.class}, name = "withId")
interface RoleProjection {
    @Value("#{target.id}")
    Long getId();

    String getName();


}
