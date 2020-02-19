package ru.ilnik.garage;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ru.ilnik.garage.config.AppProperties;

@EnableJpaAuditing
@EnableAdminServer
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class GarageApplication {
    public static void main(String[] args) {
        SpringApplication.run(GarageApplication.class, args);
    }
}
