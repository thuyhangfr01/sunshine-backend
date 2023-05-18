package com.ute.sunshinebackend.controller.User;

import java.util.ArrayList;
import java.util.List;
import com.ute.sunshinebackend.entity.User.Role;
import com.ute.sunshinebackend.repository.User.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
    @Autowired
    RoleRepository roleRepository;

    // Get all Users
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles() {

        List<Role> roles = new ArrayList<Role>();
        roleRepository.findAll().forEach(roles::add);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
