package com.ttrpg.application;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.net.URL;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
class TTRPGHelperApplicationTests {
    TestRestTemplate restTemplate;
    URL base;
    @LocalServerPort
    int port = 8081;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void setUpUser() {
        usersRepository.deleteAll();
        User newUser = new User();
        newUser.setUsername("newUser");
        newUser.setPassword(passwordEncoder.encode("newPassword"));
        usersRepository.save(newUser);
    }

    @Test
    public void testFailBasicAuthentication()
            throws IllegalStateException, IOException {
        restTemplate = new TestRestTemplate("foo", "foo1");
        base = new URL("http://localhost:" + port + "/auth/all");
        ResponseEntity<String> response =
                restTemplate.getForEntity(base.toString(), String.class);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testPassBasicAuthentication()
            throws Exception {
        setUpUser();
        restTemplate = new TestRestTemplate("newUser","newPassword");
        base = new URL("http://localhost:" + port + "/auth/all");
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString(), String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}