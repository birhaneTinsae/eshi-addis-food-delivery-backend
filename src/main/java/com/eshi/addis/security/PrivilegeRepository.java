package com.eshi.addis.security;


import com.enatsystem.hr.job.JobPosition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;

import java.util.Optional;

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
