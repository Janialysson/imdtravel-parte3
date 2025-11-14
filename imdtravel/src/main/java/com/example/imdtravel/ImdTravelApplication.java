package com.example.imdtravel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ImdtravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImdtravelApplication.class, args);
    }
}