package com.ute.sunshinebackend.controller;

import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    //api get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return userService.getAllUser();
    }

    //api get user by id
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id){
        return userService.getUserById(id);
    }

    //api update user
    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user){
        return userService.updateUser(id, user);
    }

    //api delete user
    @DeleteMapping("/user/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id){
        return userService.deleteUserById(id);
    }

}
