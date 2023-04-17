package com.ute.sunshinebackend.controller;

import com.ute.sunshinebackend.entity.Role;
import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.repository.RoleRepository;
import com.ute.sunshinebackend.repository.UserRepository;
import com.ute.sunshinebackend.security.JWTTokenHelper;
import com.ute.sunshinebackend.security.requests.AuthenticationRequest;
import com.ute.sunshinebackend.security.requests.SignupRequest;
import com.ute.sunshinebackend.security.responses.LoginResponse;
import com.ute.sunshinebackend.security.responses.MessageResponse;
import com.ute.sunshinebackend.security.responses.UserInfo;
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
import java.util.*;
import java.util.stream.Collectors;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenHelper jwtTokenHelper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email đã tồn tại!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getPhone(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        strRoles.forEach(role -> {
            switch (role) {
                case "ROLE_ADMIN":
                    Role adminRole = roleRepository.findById(Long.valueOf(1))
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);

                    break;
                case "ROLE_COLLABORATOR":
                    Role collaRole = roleRepository.findById(Long.valueOf(2))
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(collaRole);

                    break;
                case "ROLE_BENEFACTOR":
                    Role beneRole = roleRepository.findById(Long.valueOf(3))
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(beneRole);

                    break;
                case "ROLE_RECIPIENT":
                    Role recipRole = roleRepository.findById(Long.valueOf(4))
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(recipRole);

                    break;
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Đăng ký thành công!"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user=(User)authentication.getPrincipal();
        String jwtToken=jwtTokenHelper.generateToken(user.getEmail());

        User userDetails = (User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        LoginResponse response=new LoginResponse(jwtToken,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getPassword(),
                user.getStreet(),
                user.getId_ward(),
                roles);
        response.setToken(jwtToken);
        return ResponseEntity.ok(response);
    }

}
