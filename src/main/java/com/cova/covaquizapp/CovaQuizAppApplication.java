package com.cova.covaquizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CovaQuizAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CovaQuizAppApplication.class, args);
    }

}
