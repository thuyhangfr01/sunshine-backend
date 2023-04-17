package com.ute.sunshinebackend.controller;

import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.security.responses.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
@RequestMapping("/api/info")
@CrossOrigin
public class UserController {
    //    public ResponseEntity<?> getUserInfo(Principal user){
//        User userObj=(User) userDetailsService.loadUserByUsername(user.getName());
//
//        UserInfo userInfo=new UserInfo();
//        userInfo.setId(userObj.getId());
//        userInfo.setName(userObj.getName());
//        userInfo.setEmail(userObj.getEmail());
//        userInfo.setPassword(userObj.getPassword());
//        userInfo.setRoles(userObj.getAuthorities().toArray());
//
//        return ResponseEntity.ok(userInfo);
//    }

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


    @GetMapping("/forRecipient")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR') or hasRole('RECIPIENT')")
    public String recipAccess() {
        return "Recipient Board.";
    }

    @GetMapping("/forBenefactor")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COLLABORATOR') or hasRole('BENEFACTOR')")
    public String beneAccess() {
        return "Benefactor Board.";
    }
}
