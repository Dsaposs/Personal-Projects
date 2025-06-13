package com.ttrpg.auth.controllers;

import com.ttrpg.auth.repositories.entities.User;
import com.ttrpg.auth.services.AuthService;
import com.ttrpg.helper.enums.Roles;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="authorization")
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<UserDetails> getUser(@RequestParam("u") String name){
        UserDetails details = authService.loadUserByUsername(name);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<String> addNewUser (@RequestParam String u, @RequestParam String p) {
        User n = new User();
        n.setUsername(u);
        n.setPassword(passwordEncoder.encode(p));
        n.setRole(Roles.USER.getRole());
        authService.save(n);
        return new ResponseEntity<>("New User Created", HttpStatus.OK);
    }

    @PostMapping(path="/add/{gameId}")
    public ResponseEntity<String> addNewTempUser (@PathParam ("gameId") Integer gameId, @RequestParam String u) {
        User n = new User();
        n.setUsername(u);
        n.setRole(Roles.TEMP_USER.getRole());
        n.setPassword(passwordEncoder.encode(u + "#" + gameId));
        authService.save(n);
        return new ResponseEntity<>("New Temporary User Created", HttpStatus.OK);
    }

    //TODO move this to admin controller
//    @PreAuthorize("hasRole(Roles.ADMIN.getRole())")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return authService.findAll();
    }
}