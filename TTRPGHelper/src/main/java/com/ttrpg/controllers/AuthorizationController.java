package com.ttrpg.controllers;

import com.ttrpg.entities.User;
import com.ttrpg.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="auth")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String u, @RequestParam String p) {
        User n = new User();
        n.setUsername(u);
        n.setPassword(passwordEncoder.encode(p));
        authorizationService.save(n);
        return "Saved";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return authorizationService.findAll();
    }
}
