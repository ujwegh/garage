package ru.ilnik.garage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.ilnik.garage.config.AppProperties;

@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class GarageApplication {
    public static void main(String[] args) {
        SpringApplication.run(GarageApplication.class, args);
    }
}
