package com.eshi.addis.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableJpaRepositories(repositoryBaseClass = NaturalRepositoryImpl.class, basePackages = "com.eshi.addis")
public class JpaEnversConfiguration {

    @Bean
    AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpl();
    }
}
