package com.eshi.addis;

import com.eshi.addis.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class EshiAddisFoodDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshiAddisFoodDeliveryApplication.class, args);
    }

}
