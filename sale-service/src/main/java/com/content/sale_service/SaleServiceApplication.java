package com.content.sale_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SaleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SaleServiceApplication.class, args);
    }
}
