package com.eshi.addis;


import com.eshi.addis.security.user.User;
import com.eshi.addis.security.user.UserRepository;
import com.eshi.addis.storage.StorageProperties;
import com.eshi.addis.utils.ApplicationProps;
import com.google.maps.GeoApiContext;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Log4j2
@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class, ApplicationProps.class})
public class EshiAddisFoodDeliveryApplication {

    @Value("${apiKey}")
    private String apiKey;

    public static void main(String[] args) {
        SpringApplication.run(EshiAddisFoodDeliveryApplication.class, args);
    }


    @Bean
    public GeoApiContext getGeoApiContext() {
        return new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();
    }

    @RequiredArgsConstructor
    @Component
    class CreatUser implements CommandLineRunner {
        private final UserRepository repository;
        private final PasswordEncoder encoder;

        @Override
        public void run(String... args) throws Exception {
            Optional<User> admin = repository.findByUsername("Admin");
            if (admin.isEmpty()) {
                var user = new User();
                user.setUsername("Admin");
                user.setPassword(encoder.encode("6DKgfJLT56de6jFp_ "));
                user.setEnabled(true);
                user.setAccountNonExpired(true);
                user.setAccountNonLocked(true);
                user.setActive(true);
                user.setCredentialsNonExpired(true);

                repository.save(user);
            }

            var key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            log.info(Encoders.BASE64.encode(key.getEncoded()));

        }

    }
}
