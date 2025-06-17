package com.example.jirakid;

import com.example.jirakid.config.CorsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Import({CorsConfig.class})
@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackages = "com.example.jirakid")

public class JiraKidApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiraKidApplication.class, args);

    }
}
