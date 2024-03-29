package com.eshi.addis.config;

import org.hibernate.NaturalIdLoadAccess;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

@Transactional(readOnly = true)
public class NaturalRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements com.eshi.addis.config.NaturalRepository<T, ID> {
    private final EntityManager entityManager;

    public NaturalRepositoryImpl(JpaEntityInformation entityInformation,
                                 EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findBySimpleNaturalId(ID naturalId) {
        Optional<T> entity = entityManager.unwrap(Session.class)
                .bySimpleNaturalId(this.getDomainClass())
                .loadOptional(naturalId);
        return entity;
    }


    @Override
    public Optional<T> findByNaturalId(Map<String, Object> naturalIds) {
        NaturalIdLoadAccess<T> loadAccess
                = entityManager.unwrap(Session.class).byNaturalId(this.getDomainClass());
        naturalIds.forEach(loadAccess::using);
        return loadAccess.loadOptional();
    }
}
