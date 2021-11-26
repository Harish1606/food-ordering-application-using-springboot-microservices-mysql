package com.ford.foa_authentication_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FoaAuthenticationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoaAuthenticationServiceApplication.class, args);
    }

}
