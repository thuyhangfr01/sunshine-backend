package com.ute.sunshinebackend.service;

import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.repository.UserRepository;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public ResponseEntity<User> updateUser(long id, User user) {
        if(user!=null){
            Optional<User> userData = userRepository.findById(id);
            if(userData.isPresent()) {
                User _user = userData.get();
                _user.setName(user.getName());
                _user.setEmail(user.getEmail());
                _user.setPhone(user.getPhone());
                _user.setPassword(getEncodedPassword(user.getPassword()));
                _user.setStreet(user.getStreet());
                _user.setId_ward(user.getId_ward());

                return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteUserById(long id) {
        try{
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<User>> getAllUser() {
        try{
            List<User> users = new ArrayList<User>();

            userRepository.findAll().forEach(users::add);

            if (users.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<User> getUserById(long id) {
        try {
            Optional<User> userData = userRepository.findById(id);
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> updateRoleById(long idUser, long idRole) {
        try{
            userRepository.findRoleIdByUserId(idUser, idRole);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
