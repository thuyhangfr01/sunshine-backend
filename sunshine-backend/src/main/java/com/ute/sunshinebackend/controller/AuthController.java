package com.ute.sunshinebackend.controller;

import com.ute.sunshinebackend.entity.ERole;
import com.ute.sunshinebackend.entity.Role;
import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.repository.RoleRepository;
import com.ute.sunshinebackend.repository.UserRepository;
import com.ute.sunshinebackend.security.jwt.JwtUtils;
import com.ute.sunshinebackend.security.payload.requests.LoginRequest;
import com.ute.sunshinebackend.security.payload.requests.SignupRequest;
import com.ute.sunshinebackend.security.payload.responses.JwtResponse;
import com.ute.sunshinebackend.security.payload.responses.MessageResponse;
import com.ute.sunshinebackend.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getEmail(),
                userDetails.getPhone(),
                userDetails.getPassword(),
                userDetails.getStreet(),
                userDetails.getId_ward(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhone(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_COLLABORATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "collaborator":
                        Role collaRole = roleRepository.findByName(ERole.ROLE_COLLABORATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(collaRole);

                        break;
                    case "benefactor":
                        Role beneRole = roleRepository.findByName(ERole.ROLE_BENEFACTOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(beneRole);

                        break;
                    case "recipient":
                        Role recipRole = roleRepository.findByName(ERole.ROLE_RECIPIENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(recipRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_COLLABORATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
