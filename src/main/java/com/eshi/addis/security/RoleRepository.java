package com.eshi.addis.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "roles",path = "roles",excerptProjection = RoleProjection.class)
public interface RoleRepository extends PagingAndSortingRepository< Role,Long> {
    Page<Role> findAllByDeletedAtNull(Pageable pageable);

    Optional<Role> findByName(String name);
}
@Projection(types ={Role.class},name = "withId")
interface RoleProjection{
    @Value("#{target.id}")
    Long getId();
    String getName();


}
