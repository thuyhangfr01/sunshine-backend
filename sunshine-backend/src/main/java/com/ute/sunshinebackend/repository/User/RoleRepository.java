package com.ute.sunshinebackend.repository.User;

import com.ute.sunshinebackend.entity.User.ERole;
import com.ute.sunshinebackend.entity.User.Role;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
