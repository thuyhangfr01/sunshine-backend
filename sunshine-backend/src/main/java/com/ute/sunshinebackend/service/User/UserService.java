package com.ute.sunshinebackend.service.User;

import com.ute.sunshinebackend.entity.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    //update user
    public ResponseEntity<User> updateUser(long id, User user);

    //delete user by id
    public ResponseEntity<HttpStatus> deleteUserById(long id);

    //get all users
    public ResponseEntity<List<User>> getAllUser();

    //get user by id
    public ResponseEntity<User> getUserById(long id);

    //update role by user
    public ResponseEntity<HttpStatus> updateRoleById(long idUser, long idRole);
}
