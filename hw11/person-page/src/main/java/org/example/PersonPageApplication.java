package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PersonPageApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonPageApplication.class, args);
    }
}
