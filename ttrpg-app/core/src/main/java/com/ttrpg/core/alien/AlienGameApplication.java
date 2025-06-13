package com.ttrpg.core.alien;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AlienGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlienGameApplication.class, args);
    }

    @GetMapping("/home")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String helloUser(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello User %s!", name);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String helloAdmin(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello Admin %s!", name);
    }
}