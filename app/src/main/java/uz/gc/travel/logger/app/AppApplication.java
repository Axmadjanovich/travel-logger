package uz.gc.travel.logger.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "uz.gc")
@ConfigurationPropertiesScan(basePackages = "uz.gc")
@SpringBootApplication(scanBasePackages = "uz.gc")
@ComponentScan(basePackages = "uz.gc")
public class AppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

}
