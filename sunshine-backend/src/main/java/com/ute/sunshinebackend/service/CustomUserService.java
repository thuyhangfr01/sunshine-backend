package com.ute.sunshinebackend.service;

import com.ute.sunshinebackend.entity.User;
import com.ute.sunshinebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    private
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username); //username = email

        if(null == user){
            throw new UsernameNotFoundException("User not found with email " + username);
        }
        return user;
    }
}
