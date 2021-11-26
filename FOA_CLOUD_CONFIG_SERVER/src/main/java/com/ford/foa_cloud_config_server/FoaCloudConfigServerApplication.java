package com.ford.foa_cloud_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class FoaCloudConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoaCloudConfigServerApplication.class, args);
    }

}
