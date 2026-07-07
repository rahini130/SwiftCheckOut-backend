package com.example.SwiftCheckOut.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.SwiftCheckOut.entity.Customer;
import com.example.SwiftCheckOut.repository.CustomerRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository repo;

    public CustomUserDetailsService(CustomerRepository repo) {
        this.repo = repo;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer user = repo.findByEmail(email).orElseThrow(() ->new UsernameNotFoundException("User Not Found !"));
        return User.builder()
               .username(user.getEmail())
               .password(user.getPassword())
               .roles(user.getRole().name())
               .build();
    }
    
}
