package com.ttrpg.auth.controllers;

import com.ttrpg.auth.services.AuthService;
import com.ttrpg.helper.services.auth.dto.UserDTO;
import com.ttrpg.helper.services.auth.dto.UserDetailsDTO;
import com.ttrpg.helper.services.auth.entites.User;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ttrpg.helper.services.auth.AuthConstants.*;

@RestController
@RequestMapping(path=AUTHORIZATION_URI)
public class AuthController {
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(AuthService authService, PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<UserDetailsDTO> getUser(@RequestParam("u") String name) {
        UserDetails details = authService.loadUserByUsername(name);
        List<String> roles = details.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(auth -> auth.startsWith("ROLE_") ? auth.substring(5) : auth)
                .toList();
        UserDetailsDTO resp = new UserDetailsDTO(
                details.getUsername(),
                details.getPassword(),
                roles
        );
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping(path=AUTHORIZATION_ADD_URI)
    public ResponseEntity<String> addNewUser (@RequestParam String u, @RequestParam String p) {
        User n = new User();
        n.setUsername(u);
        n.setPassword(passwordEncoder.encode(p));
        n.setRole(USER);
        authService.save(n);
        return new ResponseEntity<>("New User Created", HttpStatus.OK);
    }

    @PostMapping(path=AUTHORIZATION_ADD_TEMP_URI)
    public ResponseEntity<String> addNewTempUser (@PathParam (TEMP_USER_HOST_ID) Integer gameId, @RequestParam String u) {
        User n = new User();
        n.setUsername(u);
        n.setRole(TEMP_USER);
        n.setPassword(passwordEncoder.encode(u + "#" + gameId));
        authService.save(n);
        return new ResponseEntity<>("New Temporary User Created", HttpStatus.OK);
    }

    //TODO add admin/service authorization requirement
    @GetMapping(path=AUTHORIZATION_ALL_URI)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return new ResponseEntity<>(authService.findAll().stream().map(UserDTO::convertEntityToDto).toList(), HttpStatus.OK);
    }
}