package com.dreamer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerAnotherApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerAnotherApplication.class, args);
    }

}
