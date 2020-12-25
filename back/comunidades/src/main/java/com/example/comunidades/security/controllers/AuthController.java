package com.example.comunidades.security.controllers;

import com.example.comunidades.security.exceptions.SignupException;
import com.example.comunidades.security.jwt.JwtUtils;
import com.example.comunidades.security.models.ERole;
import com.example.comunidades.security.models.RoleEntity;
import com.example.comunidades.security.models.UserEntity;
import com.example.comunidades.security.payload.request.LoginRequest;
import com.example.comunidades.security.payload.request.SignupRequest;
import com.example.comunidades.security.payload.response.JwtResponse;
import com.example.comunidades.security.payload.response.MessageResponse;

import javax.validation.Valid;

import com.example.comunidades.security.repository.RoleRepository;
import com.example.comunidades.security.repository.UserRepository;
import com.example.comunidades.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = { "http://localhost:8081" })
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
                          UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }


    @GetMapping("/user")
    public UserDetails user() {
        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
        }
        return userDetails;
    }


    @PostMapping("/signin")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }


    @PostMapping("/signup")
    public MessageResponse registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            throw new SignupException("Error: Username is already taken!");
        }
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new SignupException("Error: Email is already taken!");
        }
        // Create new user's account
        UserEntity user = new UserEntity(signupRequest.getUsername(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()));
        // Get default roles (USER)
        Set<RoleEntity> roles = new HashSet<>();
        RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new SignupException("Error: Role is not found."));
        roles.add(userRole);

        // Add roles to user & save user to DB
        user.setRoles(roles);
        userRepository.save(user);

        return new MessageResponse("User registered sucessfully!");
    }

}
