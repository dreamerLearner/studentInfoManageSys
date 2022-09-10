package com.dreamer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author dreamer
 * @creater 2022/6/19 0019 17:35
 */
@EnableEurekaClient
@SpringBootApplication
@MapperScan("com/dreamer/mapper")
public class EurekaProviderAnotherApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaProviderAnotherApplication.class, args);
    }
}
