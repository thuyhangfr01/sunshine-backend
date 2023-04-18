package com.ute.sunshinebackend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/info")
public class UserController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
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
