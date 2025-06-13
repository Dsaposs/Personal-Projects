package com.ttrpg.users.controllers;

import com.ttrpg.users.repositories.entities.User;
import com.ttrpg.users.services.AuthorizationService;
import com.ttrpg.users.services.GameService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path="auth")
public class AuthorizationController {
    private final AuthorizationService authorizationService;
    private final PasswordEncoder passwordEncoder;
    private final GameService gameService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService, PasswordEncoder passwordEncoder, GameService gameService) {
        this.authorizationService = authorizationService;
        this.passwordEncoder = passwordEncoder;
        this.gameService = gameService;
    }

    @PostMapping(path="/add")
    public ResponseEntity<String> addNewUser (@RequestParam String u, @RequestParam String p) {
        User n = new User();
        n.setUsername(u);
        n.setPassword(passwordEncoder.encode(p));
        n.setRole("USER");
        authorizationService.save(n);
        return new ResponseEntity<>("New User Created", HttpStatus.OK);
    }

    @PostMapping(path="/add/{gameId}")
    public ResponseEntity<String> addNewTempUser (@PathParam ("gameId") Integer gameId, @RequestParam String u) {
        if(gameService.getGameById(gameId) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game Not Found");

        User n = new User();
        n.setUsername(u);
        n.setRole("TEMP_USER");
        n.setPassword(passwordEncoder.encode(u + "#" + gameId));
        authorizationService.save(n);
        return new ResponseEntity<>("New Temporary User Created", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return authorizationService.findAll();
    }
}