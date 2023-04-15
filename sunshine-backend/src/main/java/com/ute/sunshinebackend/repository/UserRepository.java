package com.ute.sunshinebackend.repository;

import java.util.*;
import com.ute.sunshinebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
