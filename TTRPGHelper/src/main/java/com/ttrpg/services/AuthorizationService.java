package com.ttrpg.services;

import com.ttrpg.entities.User;
import com.ttrpg.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles("USER")
                        .build();
            }
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User n) {
        userRepository.save(n);
    }
}