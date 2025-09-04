package com.content.descubridor_servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DescubridorServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(DescubridorServicioApplication.class, args);
    }

}