package com.panchayat.locals.service;

import com.panchayat.locals.entity.User;
import com.panchayat.locals.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repo;

    public CustomUserDetailsService(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {

        User user = repo.findByMobile(mobile)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getMobile())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}