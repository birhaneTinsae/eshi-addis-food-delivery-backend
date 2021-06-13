package com.eshi.addis.security.privilage;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryRestResource(collectionResourceRel = "privileges", path = "privileges", excerptProjection = PrivilegeProjection.class)
public interface PrivilegeRepository extends PagingAndSortingRepository<Privilege, Long> {
    Page<Privilege> findAllByDeletedAtNull(Pageable pageable);

    Optional<Privilege> findByName(String name);
}

@Projection(name = "PrivilegeProjection", types = Privilege.class)
interface PrivilegeProjection {
    @Value("#{target.id}")
    String getId();

    String getName();
}
