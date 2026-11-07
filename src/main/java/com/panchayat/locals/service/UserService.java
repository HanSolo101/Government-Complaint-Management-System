package com.panchayat.locals.service;

import com.panchayat.locals.dto.RegisterRequest;
import com.panchayat.locals.dto.UserResponse;
import com.panchayat.locals.entity.Role;
import com.panchayat.locals.entity.User;
import com.panchayat.locals.exception.UserAlreadyExistsException;
import com.panchayat.locals.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.existsByMobile(request.getMobile())) {
            throw new UserAlreadyExistsException("User with mobile number " + request.getMobile() + " already exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setMobile(request.getMobile());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.CITIZEN);

        return userRepository.save(user);
    }

    public User getByMobile(String mobile) {
        return userRepository.findByMobile(mobile)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mobile: " + mobile));
    }

    public UserResponse getUserProfile(String mobile) {
        User user = getByMobile(mobile);
        return UserResponse.fromEntity(user);
    }
}
