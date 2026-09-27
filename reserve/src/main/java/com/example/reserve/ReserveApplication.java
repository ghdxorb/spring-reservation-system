package com.example.reserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.reserve")
public class ReserveApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReserveApplication.class, args);
    }
}