package com.example.rest.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("defaultUser".equals(username)) {
            return new User("defaultUser", "{noop}defaultPassword", Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}