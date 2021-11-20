package com.jx2lee.sfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SfoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SfoApplication.class);
    }
}
