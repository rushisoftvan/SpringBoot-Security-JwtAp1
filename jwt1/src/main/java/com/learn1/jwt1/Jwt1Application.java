package com.learn1.jwt1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Jwt1Application {

    public static void main(String[] args) {
        SpringApplication.run(Jwt1Application.class, args);
        long millis = TimeUnit.MINUTES.toMillis(1);
        System.out.println(millis);
        Instant instant = Instant.now().plusMillis(21000000);
        System.out.println(instant);
    }

}
