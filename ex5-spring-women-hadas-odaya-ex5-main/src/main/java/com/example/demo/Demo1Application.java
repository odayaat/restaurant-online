package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Demo1Application class is the entry point for the Spring Boot application.
 * It contains the main method which launches the application.
 */
@SpringBootApplication
public class Demo1Application {

    /**
     * The main method which serves as the entry point for the Spring Boot application.
     * It runs the application using SpringApplication.run().
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }
}
