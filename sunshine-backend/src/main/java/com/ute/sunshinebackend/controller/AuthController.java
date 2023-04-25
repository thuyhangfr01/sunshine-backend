package com.ute.sunshinebackend.controller;

import com.ute.sunshinebackend.entity.ERole;
import com.ute.sunshinebackend.entity.RefreshToken;
import com.ute.sunshinebackend.entity.Role;
import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.exception.TokenRefreshException;
import com.ute.sunshinebackend.repository.RoleRepository;
import com.ute.sunshinebackend.repository.UserRepository;
import com.ute.sunshinebackend.security.jwt.JwtUtils;
import com.ute.sunshinebackend.security.payload.requests.LoginRequest;
import com.ute.sunshinebackend.security.payload.requests.SignupRequest;
import com.ute.sunshinebackend.security.payload.requests.TokenRefreshRequest;
import com.ute.sunshinebackend.security.payload.responses.JwtResponse;
import com.ute.sunshinebackend.security.payload.responses.MessageResponse;
import com.ute.sunshinebackend.security.payload.responses.TokenRefreshResponse;
import com.ute.sunshinebackend.security.service.UserDetailsImpl;
import com.ute.sunshinebackend.security.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

//        return ResponseEntity.ok(new JwtResponse(jwt,
//                refreshToken.getToken(),
//                userDetails.getId(),
//                userDetails.getName(),
//                roles));

        return ResponseEntity.ok(new JwtResponse(jwt,
                refreshToken.getToken(),
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
            }
        });

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getEmail());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = userDetails.getId();
        refreshTokenService.deleteByUserId(userId);
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/collaborator")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR')")
    public String collaAccess() {
        return "Collaborator Board.";
    }


    @GetMapping("/recipient")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR') or hasRole('RECIPIENT')")
    public String recipAccess() {
        return "Recipient Board.";
    }

    @GetMapping("/benefactor")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR') or hasRole('BENEFACTOR')")
    public String beneAccess() {
        return "Benefactor Board.";
    }
}
