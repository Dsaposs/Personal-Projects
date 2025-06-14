package com.ttrpg.api.application;

import com.ttrpg.helper.services.auth.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthorizationService implements UserDetailsService {
    private final RestTemplate restTemplate;

    @Autowired
    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return AuthClient.loadUserByUsername(username, restTemplate);
    }
}