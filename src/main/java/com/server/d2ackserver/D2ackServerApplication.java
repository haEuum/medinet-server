package com.server.d2ackserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class D2ackServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(D2ackServerApplication.class, args);
    }

}
