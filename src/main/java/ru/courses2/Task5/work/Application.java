package ru.courses2.Task5.work;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.courses2.Task5"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
