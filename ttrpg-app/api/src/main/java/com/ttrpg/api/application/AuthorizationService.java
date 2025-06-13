package com.ttrpg.api.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthorizationService implements UserDetailsService {

    public static final String AUTHORIZATION_SERVICE = "http://authorization-service/";
    final RestTemplate restTemplate;

    @Autowired
    public AuthorizationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return restTemplate.getForObject(AUTHORIZATION_SERVICE + "authorization?u=" + username, UserDetails.class);
    }
}