package ovh.major.mybackendapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ovh.major.mybackendapp.infrastructure.security.jwt.JwtConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtConfigurationProperties.class})
public class MyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBackendApplication.class, args);
    }

}
