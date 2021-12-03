package io.github.jx2lee.automation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AutomationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AutomationApplication.class);
    }
}
