package com.panchayat.locals.controller;

import com.panchayat.locals.dto.AuthResponse;
import com.panchayat.locals.dto.LoginRequest;
import com.panchayat.locals.dto.RegisterRequest;
import com.panchayat.locals.dto.UserResponse;
import com.panchayat.locals.entity.User;
import com.panchayat.locals.security.JwtUtil;
import com.panchayat.locals.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = userService.register(request);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("userId", user.getId());

        String token = jwtUtil.generateToken(user.getMobile(), claims);

        AuthResponse response = new AuthResponse(
                token,
                user.getId(),
                user.getName(),
                user.getMobile(),
                user.getRole()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        // Authenticate credentials against CustomUserDetailsService & PasswordEncoder
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMobile(),
                        request.getPassword()
                )
        );

        User user = userService.getByMobile(request.getMobile());

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole().name());
        claims.put("userId", user.getId());

        String token = jwtUtil.generateToken(user.getMobile(), claims);

        AuthResponse response = new AuthResponse(
                token,
                user.getId(),
                user.getName(),
                user.getMobile(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String mobile = authentication.getName();
        return ResponseEntity.ok(userService.getUserProfile(mobile));
    }
}
